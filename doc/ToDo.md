Выпилить AbstractStorage из схемы.

Перенести connectionBreak(), connectionRequest() из AbstractNetworkMgr в Node.

consistent statment:
* view, model, controler - отдельные пакеты.
* Controler может получить из model только контейнеры, публичные поля Entity'.
* Все остальные данные не доступны из view и controller.
* Реализации Utilits должны лежать в одном пакете с Entity.

...