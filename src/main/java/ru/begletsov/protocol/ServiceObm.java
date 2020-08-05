package ru.begletsov.protocol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс ParamObm реализует сервис по добавлению новых обменов и их параметров
 * 1) создание класса
 * @author Sergei Begletsov
 * @since 05.08.2020
 * @version 1
 */

public class ServiceObm {
    /**
     * поле содержит все обмены и их параметры
     */
    private Map<Abonent, List<Param>> abonents = new HashMap<>();

    /**
     * Добавление нового абонента из протокола обмена
     * @param abonent имя абонента
     */
    public void addObmen(Abonent abonent) {
        //если кодовый обмен не найден, то добавляем его
        abonents.putIfAbsent(abonent, new ArrayList<Param>());
    }

    /**
     * Добавление нового параметра для абонента
     * @param name название абонента
     * @param newParam новый счет пользователя
     */
    public void addParam(String name, Param newParam) {
        //1. Находим обмен по названию
        Abonent abonent = findByObmen(name);

        //2. Проверяем, что обмен существует (найден)
        if (abonent != null) {

            //3. Получаем все параметры обмена для абонента
            List<Param> paramObmAll = abonents.get(abonent);

            //4. Проверяем, что обмена есть какие-то параметры (не равно null)
            if (paramObmAll != null) {

                //5. Есть обмена уже такой параметр?
                if (!paramObmAll.contains(newParam)) {
                    //5.1 Нет, параметр не найден -> добавляем его
                    paramObmAll.add(newParam);
                }
            }
        }
    }

    /**
     * Поиск обмена по его названию
     * @param nameAbonent название абонента
     * @return обмен Abonent при успешном поиске, null - если абонента не нашли
     */
    public Abonent findByObmen(String nameAbonent) {
        return abonents.keySet().stream()
                .filter(user -> user.getNameObm().equals(nameAbonent))
                .findFirst()
                .orElse(null); //.orElseGet(() -> null);
    }


}
