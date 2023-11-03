# DoctorCalendarManagementSystem

## Enviroment Setup
 - Java SDK: https://www.oracle.com/java/technologies/downloads/
 - Maven: https://maven.apache.org/download.cgi
 - PostgreSQL: https://www.postgresql.org/download/

## To Run the Project
1. Make sure you have all the environments properly setup. 
2. The Database migration would run automatically when the program is running for the first time, meaning initial database and data would be setup automatically.
3. Open an IDE and click on the green icon (so that you don't have to manually mvn compile, package and execute the java code from an executable JAR file)
   
![Screenshot 2023-11-02 at 6 11 08 PM](https://github.com/HaoZengGH/DoctorCalendarManagementSystem/assets/91028444/362f8c20-9fc3-4a0f-9d05-7806b311f151)

4. Open terminal or postman to send the request according to the following API documentation

## API Documentation

### GET localhost:8080/api/v1/doctors
Description: Get a list of all the doctors
Response: 200 OK
Response body:
```
{"doctorList":
  [
    {"id":1,"firstName":"John","lastName":"Doe"},
    {"id":2,"firstName":"Jane","lastName":"Smith"},
    {"id":3,"firstName":"Lucy","lastName":"Hart"}
  ]
}
```

### GET localhost:8080/api/v1/doctors/{doctor_id}/appointments?date={date}
Description: Get a list of all appointments for a particular doctor and particular day
Response: 200 OK

Example request:
```
localhost:8080/api/v1/doctors/1/appointments?date=2023-10-22
```
Response body:
```
{
    "appointmentList": [
        {
            "id": 2,
            "doctorId": 1,
            "patientFirstName": "Sara",
            "patientLastName": "Wilson",
            "appointmentDateTime": "2023-10-22T09:00:00",
            "kind": "Follow-up"
        }
    ]
}
```

### POST localhost:8080/api/v1/doctors/{doctor_id}/appointments
Description: Add a new appointment to a doctor's calendar
Response: 201 Created

Example request:
```
localhost:8080/api/v1/doctors/1
```
Response body:
```
successfully created a new appointment with ID: 3
```

### DELETE localhost:8080/api/v1/appointments/{appointment_id}
Description: Delete an existing appointment from a doctor's calendar
Response: 202 Accepted

Example request:
```
localhost:8080/api/v1/appointments/1
```
Response body:
```
Successfully deleted the appointment
```
