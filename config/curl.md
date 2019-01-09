### DISH CONTROLLER
#### create dish
`curl -s -X POST -d '{"name":"Created dish","price":300}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votergraduation/rest/admin/100002/dishes --user admin@gmail.com:admin`
#### get dish
`curl -s http://localhost:8080/votergraduation/rest/admin/100002/dishes/100005 --user admin@gmail.com:admin`
#### get all dish
`curl -s http://localhost:8080/votergraduation/rest/admin/100002/dishes --user admin@gmail.com:admin`

### MENU ITEM CONTROLLER
#### create menu item
`curl -s -X POST -d '{"date":"2018-11-07","time":"07:00"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votergraduation/rest/admin/100004/menu-items?dishId=100007 --user admin@gmail.com:admin`
#### get menu items by date
`curl -s http://localhost:8080/votergraduation/rest/profile/menu-items-by-date?date=2018-11-07 --user user1@yandex.ru:password1`

### RESTAURANT CONTROLLER
#### create restaurant
`curl -s -X POST -d '{"name":"Restaurant 5","phone":"222225","address":"Street 5"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votergraduation/rest/admin/restaurants --user admin@gmail.com:admin`
#### get restaurant
`curl -s http://localhost:8080/votergraduation/rest/admin/restaurants/100003 --user admin@gmail.com:admin`
#### get all restaurants
`curl -s http://localhost:8080/votergraduation/rest/admin/restaurants --user admin@gmail.com:admin`

### USER CONTROLLER
#### create user
`curl -s -X POST -d '{"name":"User2","email":"user2@yandex.ru","password":"password2", "role":"ROLE_USER"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votergraduation/rest/admin/users --user admin@gmail.com:admin`
#### get user
`curl -s http://localhost:8080/votergraduation/rest/profile --user user1@yandex.ru:password1`

### VOTE CONTROLLER
#### create vote
`curl -s -X POST -d '{"date":"2018-12-25","time":"10:00"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/curl -s http://localhost:8080/votergraduation/rest/profile/votes/100017 --user user1@yandex.ru:password1/rest/profile/votes?restaurantId=100003 --user user1@yandex.ru:password1`
#### get vote
`curl -s http://localhost:8080/votergraduation/rest/profile/votes/100017 --user user1@yandex.ru:password1`
#### update vote
`curl -s -X PUT -d '{"date":"2018-12-25","time":"10:30"}' -H 'Content-Type: application/json' http://localhost:8080/voter/rest/profile/votergraduation/100020?restaurantId=100004 --user user1@yandex.ru:password1`