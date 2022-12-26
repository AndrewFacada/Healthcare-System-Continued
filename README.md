# synergy-password1-project2

////TESTING PURPOSES////

Creating new user
POST http://localhost:8080/newuser
{
    "firstName":"",
    "lastName":"",
    "email":"",
    "password":"",
    "dateOfBirth":"", //needs to be in YYYY/MM/DD format
    "socialSecurityNumber":"",
    "address":"",
    "currentEmployee": ,
    "currentSubscriber":
}

View all accounts
GET http://localhost:8080/allaccounts


Logging in
GET http://localhost:8080/login
{
    "email":"aa@@asas",
    "password":"2323"
}


Reset password
PATCH http://localhost:8080/updatepassword
{
    "email":"",
    "socialSecurityNumber": "",
    "password":""
}


