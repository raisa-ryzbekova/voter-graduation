### DISH CONTROLLER
#### create dish
`curl -s -X POST -d '{"name":"Created dish","price":300, "restaurantId":"100002"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votergraduation/rest/admin/dishes --user admin@gmail.com:admin`
#### get dish
`curl -s http://localhost:8080/votergraduation/rest/admin/dishes/100005?restaurantId=100002 --user admin@gmail.com:admin`
#### get all dish
`curl -s http://localhost:8080/votergraduation/rest/admin/dishes?restaurantId=100002 --user admin@gmail.com:admin`

### MENU ITEM CONTROLLER
#### create menu item
`curl -s -X POST -d '{"restaurantId":"100002","dishId":"100005"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votergraduation/rest/admin/menu-items --user admin@gmail.com:admin`
#### get menu items by date
`curl -s http://localhost:8080/votergraduation/rest/profile/menu-items-by-date?date=2018-11-07 --user user@yandex.ru:password`

### RESTAURANT CONTROLLER
#### create restaurant
`curl -s -X POST -d '{"name":"Restaurant 5","phone":"222225","address":"Street 5"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votergraduation/rest/admin/restaurants --user admin@gmail.com:admin`
#### get restaurant
`curl -s http://localhost:8080/votergraduation/rest/admin/restaurants/100003 --user admin@gmail.com:admin`
#### get all restaurants
`curl -s http://localhost:8080/votergraduation/rest/admin/restaurants --user admin@gmail.com:admin`

### USER CONTROLLER
#### create user
`curl -s -X POST -d '{"name":"User2","email":"user2@yandex.ru","password":"password2","roles":["ROLE_USER"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votergraduation/rest/admin/users --user admin@gmail.com:admin`
#### get user
`curl -s http://localhost:8080/votergraduation/rest/profile --user user@yandex.ru:password`
`curl -s http://localhost:8080/votergraduation/rest/admin/users/100000 --user admin@gmail.com:admin`
#### get users
`curl -s http://localhost:8080/votergraduation/rest/admin/users --user admin@gmail.com:admin`
#### enable/disable user
`curl -s -X PUT -d '{"enabled":false}' -H 'Content-Type: application/json' http://localhost:8080/votergraduation/rest/admin/users/100000 --user admin@gmail.com:admin`
#### delete user
`curl -s -X DELETE http://localhost:8080/votergraduation/rest/profile --user user@yandex.ru:password`

### VOTE CONTROLLER
#### create vote
`curl -s -X POST -d '{"restaurantId":"100003"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votergraduation/rest/profile/votes --user user@yandex.ru:password`
#### get vote
`curl -s http://localhost:8080/votergraduation/rest/profile/vote?date=2018-11-07 --user user@yandex.ru:password`
#### update vote
`curl -s -X PUT -d '{"date":"2018-11-07","restaurantId":"100004"}' -H 'Content-Type: application/json' http://localhost:8080/votergraduation/rest/profile/votes --user user@yandex.ru:password`