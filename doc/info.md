TODO:
* update the diagram.
* add method for self-deleting node from nodeList in connection.
* write implementation for entity' ticks.

Code stale:
* Always use double, never float.
* Always align variable declarations into columns.

Consistent statment:
* view, model, controler - отдельные пакеты.
* Controler может получить из model только контейнеры, публичные поля Entity.
* Все остальные данные не доступны из view и controller.
* Контроллер (MVC) состоит из классов Controller, DataCollector, AbstractConfig.
* DataCollector - логирует state model'a и его изменения. Преобразует данные в форму удобную для view.
* AbstractConfig - служит для кофигурации конкретной симуляции (удобен для конечного пользователя).
