package org.example;

import java.lang.reflect.Field;

/**
 * Утиліта для створення SQL-запитів (INSERT, SELECT, UPDATE, DELETE)
 */
public class SQLGenerator {

    /**
     * Генерує INSERT запит для заданого об'єкта
     *
     * @param object об'єкт, для якого потрібно згенерувати запит
     * @return рядок String, що містить згенерований INSERT запит
     * @throws IllegalAccessException у випадку невдалого доступу до поля
     */
    public static String generateInsertQuery(Object object) throws IllegalAccessException {
        Class<?> clas = object.getClass();
        checkTableAnnotation(clas);

        Table table = clas.getAnnotation(Table.class);
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        boolean first = true;

        for (Field field : clas.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                if (!first) {
                    columns.append(", ");
                    values.append(", ");
                }
                Column column = field.getAnnotation(Column.class);
                columns.append(column.name());
                field.setAccessible(true);
                values.append("'").append(field.get(object)).append("'");
                first = false;
            }
        }

        return String.format("INSERT INTO %s (%s) VALUES (%s);", table.name(), columns, values);
    }

    /**
     * Генерує SELECT запит для заданого класу
     *
     * @param clas об'єкт, для якого потрібно згенерувати запит
     * @return рядок String, що містить згенерований SELECT запит
     */
    public static String generateSelectQuery(Class<?> clas) {
        checkTableAnnotation(clas);

        Table table = clas.getAnnotation(Table.class);
        return String.format("SELECT * FROM %s;", table.name());
    }

    /**
     * Генерує UPDATE запит для заданого об'єкта
     *
     * @param object об'єкт, для якого потрібно згенерувати запит
     * @return рядок String, що містить згенерований UPDATE запит
     * @throws IllegalAccessException у випадку невдалого доступу до поля
     */
    public static String generateUpdateQuery(Object object) throws IllegalAccessException {
        Class<?> clas = object.getClass();
        checkTableAnnotation(clas);

        Table table = clas.getAnnotation(Table.class);
        StringBuilder setClause = new StringBuilder();
        String primaryKeyCondition = "";
        boolean first = true;

        for (Field field : clas.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                field.setAccessible(true);
                if (column.primaryKey()) {
                    primaryKeyCondition = String.format("%s = '%s'", column.name(), field.get(object));
                } else {
                    if (!first) {
                        setClause.append(", ");
                    }
                    setClause.append(String.format("%s = '%s'", column.name(), field.get(object)));
                    first = false;
                }
            }
        }

        if (primaryKeyCondition.isEmpty()) {
            throw new IllegalArgumentException("No primary key found for update query.");
        }

        return String.format("UPDATE %s SET %s WHERE %s;", table.name(), setClause, primaryKeyCondition);
    }

    /**
     * Генерує DELETE запит для заданого об'єкта
     *
     * @param object об'єкт, для якого потрібно згенерувати запит
     * @return рядок String, що містить згенерований DELETE запит
     * @throws IllegalAccessException у випадку невдалого доступу до поля
     */
    public static String generateDeleteQuery(Object object) throws IllegalAccessException {
        Class<?> clas = object.getClass();
        checkTableAnnotation(clas);

        Table table = clas.getAnnotation(Table.class);
        String primaryKeyCondition = "";

        for (Field field : clas.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                if (column.primaryKey()) {
                    field.setAccessible(true);
                    primaryKeyCondition = String.format("%s = '%s'", column.name(), field.get(object));
                    break;
                }
            }
        }

        if (primaryKeyCondition.isEmpty()) {
            throw new IllegalArgumentException("No primary key found for delete query.");
        }

        return String.format("DELETE FROM %s WHERE %s;", table.name(), primaryKeyCondition);
    }

    /**
     * Перевіряє, чи заданий клас має анотацію @Table
     *
     * @param clas задає клас для перевірки
     * @throws IllegalArgumentException у випадку якщо клас не анотовано з @Table
     */
    private static void checkTableAnnotation(Class<?> clas) {
        if (!clas.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Class must be annotated with @Table");
        }
    }
}
