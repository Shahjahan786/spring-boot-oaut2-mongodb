# spring-boot-oaut2-mongodb

#1. Install mongddb, start mongod service and Run Project

#2.Update user credential information

use mydb
db.user.update({"username": "samoon"}, {$set:{"enabled":true, "credentialsNonExpired":true, "accountNonLocked":true, "accountNonExpired":true}})

#To get client access_token:

#Request:

curl -X POST \
'http://localhost:8080/oauth/token?grant_type=client_credentials&deviceId=123' \
-H 'Accept: */*' \
-H 'Authorization: Basic dGVzdC1jbGllbnQ6c2VjcmV0' \
-H 'Cache-Control: no-cache' \
-H 'Connection: keep-alive' \
-H 'Host: localhost:8080' \
-H 'Postman-Token: 1e07da43-70c8-4198-a056-a1966fd9df74,f9d52550-f644-4dd5-ad27-cdd958efee78' \
-H 'User-Agent: PostmanRuntime/7.13.0' \
-H 'accept-encoding: gzip, deflate' \
-H 'cache-control: no-cache' \
-H 'content-length: '

#Response:

{
"status": "200",
"message": "Success",
"data": {
"access_token": "1555455a-5fcc-4786-a4a4-0d8a60f7f15b",
"token_type": "bearer",
"expires_in": 43199,
"scope": "all another-scope"
},
"error": null
}

To get user access_token

#Request: 

curl -X POST \
'http://localhost:8080/oauth/token?grant_type=password&username=samoon&password=L@ksol123&deviceId=123' \
-H 'Accept: */*' \
-H 'Authorization: Basic dGVzdC1jbGllbnQ6c2VjcmV0' \
-H 'Cache-Control: no-cache' \
-H 'Connection: keep-alive' \
-H 'Host: localhost:8080' \
-H 'Postman-Token: 24d39f7d-e9e6-4849-85ee-28b0cb97dcd7,180f2221-7bc4-47b7-bae5-9ebca1f452b9' \
-H 'User-Agent: PostmanRuntime/7.13.0' \
-H 'accept-encoding: gzip, deflate' \
-H 'cache-control: no-cache' \
-H 'content-length: '

#Response:

{
"status": "200",
"message": "Success",
"data": {
"access_token": "67e06ca1-9123-4869-8afe-54532f27e1ee",
"token_type": "bearer",
"refresh_token": "fdee7272-66fe-42b8-93de-f38c02e35b83",
"expires_in": 43199,
"scope": "all read-foo",
"user": "Shahjahan Samoon"
},
"error": null
}


#To get access_token from refresh_token

#Request:

curl -X POST \
'http://localhost:8080/oauth/token?grant_type=refresh_token&refresh_token=fdee7272-66fe-42b8-93de-f38c02e35b83&deviceId=123' \
-H 'Accept: */*' \
-H 'Authorization: Basic dGVzdC1jbGllbnQ6c2VjcmV0' \
-H 'Cache-Control: no-cache' \
-H 'Connection: keep-alive' \
-H 'Host: localhost:8080' \
-H 'Postman-Token: 770fe80a-6da2-43b8-80f2-b110a02a7965,08933e76-499b-4616-b624-5e2f684c5c56' \
-H 'User-Agent: PostmanRuntime/7.13.0' \
-H 'accept-encoding: gzip, deflate' \
-H 'cache-control: no-cache' \
-H 'content-length: '

#Response:

{
"status": "200",
"message": "Success",
"data": {
"access_token": "0f7923d7-8a67-4a31-89d0-7072e16c5a4b",
"token_type": "bearer",
"refresh_token": "fdee7272-66fe-42b8-93de-f38c02e35b83",
"expires_in": 43199,
"scope": "all read-foo",
"user": "Shahjahan Samoon"
},
"error": null
}
