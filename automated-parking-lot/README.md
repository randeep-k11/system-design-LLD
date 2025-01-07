# Automated Parking Lot

## Functional Requirements
1. Register a vehicle
2. Park a vehicle
3. Retrieve a vehicle
4. Process payment

## API Specification
### Endpoints
1. `POST v1/vehicles` - register a vehicle
    * payload:
    ```json
    {
        "license_plate": "KA01MN3185",
        "vehicle_size" : "Small/Medium/large/extra_large",
        "vehicle_type" : "“Car/EV Car/Bike/Truck”"
    }
    ```
    * response:
    ```json
    {
        "vehicle_id": "44e128a5-ac7a-4c9a-be4c-224b6bf81b20",
        "license_plate": "KA01MN3185",
        "vehicle_size" : "Small/Medium/large/extra_large",
        "vehicle_type" : "Car/Bike/Truck",
        "created_at": "2025-01-10T10:00:00Z"
    }
    ```

2. `GET /v1/vehicles/{vehicleId}` - Get vehicle details
    * response:
    ```json
    {
        "vehicle_id": "44e128a5-ac7a-4c9a-be4c-224b6bf81b20",
        "license_plate": "KA01MN3185",
        "vehicle_size" : "Small/Medium/large/extra_large",
        "vehicle_type" : "Car/Bike/Truck",
        "created_at": "2025-01-10T10:00:00Z"
    }
    ```
3. `GET v1/vehicles` - List all registered vehicles
    * query params :
        * `page` : 1
        * `pageSize` : 10
          response:
    ```json
       {
        "totalItems": 50,
        "page": 1,
        "pageSize": 10,
        "data": [
                 {
                    "vehicle_id": "44e128a5-ac7a-4c9a-be4c-224b6bf81b20",
                    "license_plate": "KA01MN3185",
                    "vehicle_size" : "Small",
                    "vehicle_type" : "Car",
                    "created_at": "2025-01-10T10:00:00Z"
                 },
                {
                    "vehicle_id": "44e128a5-ac7a-4c9a-be4c-2esajgjb81b20",
                    "license_plate": "KA01MN3186",
                    "vehicle_size" : "Medium",
                    "vehicle_type" : "Car",
                    "created_at": "2025-01-10T10:00:00Z"
                 }
                ]
        }
    ```
   
4. `GET /v1/vehicles/search` - Search for a vehicle
    * query params :
        * `licensePlate` : KA01MN3185
    * response:
    ```json
    {
        "vehicle_id": "44e128a5-ac7a-4c9a-be4c-224b6bf81b20",
        "license_plate": "KA01MN3185",
        "vehicle_size" : "Small",
        "vehicle_type" : "Car",
        "created_at": "2025-01-10T10:00:00Z"
    }
    ```
5.`POST v1/parking/park` - Park a vehicle and generate ticket
   * payload:
       ```json
         {
            "vehicle_id": "44e128a5-ac7a-4c9a-be4c-2esajgjb81b20",
          }
       ```
  * response:
    ```json
          {
            "ticket_id": "12345",
            "vehicle_id": "44e128a5-ac7a-4c9a-be4c-2esajgjb81b20",
            "license_plate": "KA01MN3185",
            "parking_spot_id": "A1",
            "entry_time": "2025-01-06T12:00:00Z",
            "message": "Car parked successfully"
        }
    ```
6.`POST v1/parking/retrive` - Park a vehicle and generate ticket
    * payload:
```json
       {
            "ticket_id": "12345"
       }
```
* response:
    ```json
       {
            "ticket_id": "12345",
            "vehicle_id": "44e128a5-ac7a-4c9a-be4c-2esajgjb81b20",
            "license_plate": "KA01MN3185",
            "parking_spot_id": "A1",
            "entry_time": "2025-01-06T12:00:00Z",
            "exit_time": "2025-01-06T14:00:00Z",
            "parking_duration": "2 hours",
            "parking_charge": 50.0,
            "message": "Vehicle retrieved successfully."
    }

    ```
6.`GET GET /v1/parking/available-spaces` - return the number of available parking spaces in the parking lot
* response:
    ```json
    {
    "totalSpaces": 100,
    "availableSpaces": 8,
    "occupiedSpaces": 92,
    "spacesBySize": {
        "SMALL": 2,
        "MEDIUM": 3,
        "LARGE": 2,
        "EXTRA_LARGE": 1
        }
    }

    ```
7.`GET /v1/tickets/{ticketId}` - Get ticket details
   * response:
        ```json
       {
            "ticketId": 101,
            "licensePlate": "ABC123",
            "vehicleType": "Car",
            "parkingSpaceId": 5,
            "entry_time": "2025-01-04T12:00:00Z"
         }
     ```
8.`GET /v1/parking/available-spaces` - return the number of available parking spaces in the parking lot
* response:
  ```json
     {
    "totalSpaces": 100,
    "availableSpaces": 8,
    "occupiedSpaces": 92,
    "spacesBySize": {
        "SMALL": 2,
        "MEDIUM": 3,
        "LARGE": 2,
        "EXTRA_LARGE": 1
        }
    }

  ```
9.`POST /v1/tickets/{ticketId}/payment` - Process payment
  * request:
   ```json
        {
          "paymentAmount": 20.0,
          "paymentMethod": "Credit Card",
          "paymentToken": "tok_123456789abcdef"
        }
```
* response:
    ```json
    {
    "message": "Payment processed successfully.",
    "ticketId": 101,
    "paymentAmount": 20.0,
    "paymentStatus": "Completed",
    "transactionId": "txn_987654321",
    "timestamp": "2025-01-04T12:30:00Z"
   }
  ```

## Class Diagram

![Class Diagram](src/main/resources/images/Automated_Parking_Lot_Class_Daigram.png)

## Schema Diagram

1. CREATE TYPE vehicle_size AS ENUM ('SMALL', 'MEDIUM', 'LARGE', 'EXTRA_LARGE');
2. CREATE TYPE parking_spot_size AS ENUM ('SMALL', 'MEDIUM', 'LARGE', 'EXTRA_LARGE');
3. CREATE TYPE payment_status AS ENUM ('PENDING', 'PAID');
4. CREATE TYPE payment_method AS ENUM ('CASH', 'CARD', 'UPI');
5. CREATE TYPE ticket_status AS ENUM ('ACTIVE', 'COMPLETED');

![Schema Diagram](src/main/resources/images/Automated_Parking_Lot-Schema_Design.png)

**SQL Query** 
1. SQL Query to Count total vehicles currently parked:
    ```sql
    SELECT COUNT(*) AS total_vehicles_parked
    FROM parking_spots
    WHERE status = 'Occupied';
    ```
2.SQL Query to Count Vehicles by Type
   ```sql
    SELECT v.vehicle_type, COUNT(*) AS occupied_spots
    FROM parking_spots ps
    JOIN Vehicle v ON ps.current_vehicle_id = v.vehicle_id
    WHERE ps.status = 'Occupied'
    GROUP BY v.vehicle_type;
   ```


## Sequence Diagram

![Sequence Diagram](src/main/resources/images/Parking_Lot_Sequence_Diagram.png)