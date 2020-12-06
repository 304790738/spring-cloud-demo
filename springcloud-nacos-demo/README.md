mvn spring-boot:run

http://127.0.0.1:8111/swagger-ui.html

http://127.0.0.1:8111/sso/login?account=admin&password=admin

[mongo]


    [export]
    
mongodump -u username -p password -h dbhost<:port> -d dbname -o dbdirectory
    
    [import]
    
mongorestore -u username -p password -h <hostname><:port> -d dbname <path>

