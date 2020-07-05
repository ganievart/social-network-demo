# Social Network Demo REST API

## Build with maven

    mvn package
    
## Build with docker

    docker build -t social-network-demo .

## Run with Docker

    docker run -p 8080:8080 social-network-demo

# REST API

The REST API is described below.

## Swagger UI
Detailed API documentation could be found here:
`http://localhost:8080/swagger-ui.html`

## Posting

### Request

`PUT /api/{username}/post`

    {
      "message": "string"
    }

### Example

    curl -X PUT "http://localhost:8080/api/artur/post" -H "accept: application/json;charset=UTF-8" -H "Content-Type: application/json" -d "{ \"message\": \"string\"}"
        
## Wall
     
### Request
     
     `GET /api/{username}/wall`
     
### Example
     
         curl -X GET "http://localhost:8080/api/artur/wall" -H "accept: application/json;charset=UTF-8"
         
### Response
         
         [
           {
             "username": "artur",
             "messageText": "asasdasdasd",
             "time": "2020-07-05T22:37:52.176Z"
           }
         ]
         
## Following

### Request

`POST /api/{username}/follow`

    {
      "username": "string"
    }

### Example

    curl -X POST "http://localhost:8080/api/artur/follow" -H "accept: application/json;charset=UTF-8" -H "Content-Type: application/json" -d "{ \"username\": \"trump\"}"
    
## Timeline

### Request

`GET /api/{username}/timeline`

### Example

    curl -X GET "http://localhost:8080/api/artur/timeline" -H "accept: application/json;charset=UTF-8"

### Response

    [
      {
        "username": "mask",
        "messageText": "ssdllsdllsdld",
        "time": "2020-07-05T22:50:11.381Z"
      },
      {
        "username": "trump",
        "messageText": "asasdasdasd",
        "time": "2020-07-05T22:48:59.554Z"
      }
    ]
