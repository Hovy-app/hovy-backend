# hovy-backend

## API

### Scan

/scan/{isikukood}/{phone_number}/{qr_content}

Method: GET

Triggers the authentication process. It returns some data provided by the business identified by the content of the QR code.
The wait might be long.

Example:

http://145.14.28.154:8085/scan/38211150129/+3725205452/aaa

Returns:

```
{
  "welcome": "Welcome YARY RIBERO!",
  "shopId": 1,
  "services": [
    {
      "index": 0,
      "name": "Collect package"
    },
    {
      "index": 1,
      "name": "Financial services"
    }
  ]
}
```

### enterQueue

/enterQueue/{shopId}/{serviceId}

The parameters come from the previous call (shopId and an index from the list of services)

Method: GET

Example:

http://145.14.28.154:8085/enterQueue/1/0

Returns:

```
{
  "queueNumber": 3,
  "current": 1
}
```

### next

/next/{shopId}/{serviceId}

The parameters come from the original call (shopId and an index from the list of services)

Method: GET

Example:

http://145.14.28.154:8085/next/1/0

Returns:

```
{
  "current": 1
}
```

### feedback

/feedback

Method: POST

Requires a JSON body. Example:

```
{
  "shopId": 1,
  "rate": 1,
  "comment": "Not good at all"
}
```

Rate and comment are alternatively optional: if none is set no record will be saved.