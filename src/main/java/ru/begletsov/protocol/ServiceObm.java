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
    private Map<ProtocolObm, List<ParamObm>> obmens = new HashMap<>();

    /**
     * Добавление нового абонента из протокола обмена
     * @param obm имя пользователя
     */
    public void addObmen(ProtocolObm obm) {
        //если кодовый обмен не найден, то добавляем его
        obmens.putIfAbsent(obm, new ArrayList<ParamObm>());
    }

    /**
     * Добавление нового параметра для абонента
     * @param name название абонента
     * @param newParam новый счет пользователя
     */
    public void addParam(String name, ParamObm newParam) {
        //1. Находим обмен по названию
        ProtocolObm obm = findByObmen(name);

        //2. Проверяем, что обмен существует (найден)
        if (obm != null) {

            //3. Получаем все параметры обмена для абонента
            List<ParamObm> paramObmAll = obmens.get(obm);

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
     * Поиск обмена по названию
     * @param nameObmen название абонента
     * @return обмен ProtocolObm при успешном поиске, null - если абонента не нашли
     */
    public ProtocolObm findByObmen(String nameObmen) {
        return obmens.keySet().stream()
                .filter(user -> user.getNameObm().equals(nameObmen))
                .findFirst()
                .orElse(null); //.orElseGet(() -> null);
    }


}
