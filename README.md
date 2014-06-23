
Create the database
===================

```
mongo appdb

db.createCollection('dealer')
db.dealer.ensureIndex({"loc": "2dsphere"})
```


Insert some data
================

 ```
db.dealer.insert({"name": "My First dealer", "number": "12345", "website": "http://www.dealer1.co.uk", "phone": "01908 999999",loc: { type: "Point", coordinates: [ 40, 5 ] }})
db.dealer.insert({"name": "My Second dealer", "number": "12346", "website": "http://www.dealer3.co.uk", "phone": "01908 999999",loc: { type: "Point", coordinates: [ 41, 5 ] }})
db.dealer.insert({"name": "My Third dealer", "number": "12347", "website": "http://www.dealer3.co.uk", "phone": "01908 999999",loc: { type: "Point", coordinates: [ 44, 5 ] }})
```


Build & Run
===========

```mvn clean package; java -jar target/dealerapi-0.0.1-SNAPSHOT.jar server config.yml``` 
