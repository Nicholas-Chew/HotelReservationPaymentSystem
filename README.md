## Introduction
##### Hotel Reservation and Payment System (HRPS).
HRPS is an application to computerize the processes of making hotel reservation, recording of orders
and displaying of records. It is solely used by the hotel staff.

##### The following are information about the application:
###### About Guest
1. Guests can be added with details like name, credit card details, address, country, gender, identity,
nationality, contact when check in.
2. Identity could be driving license or passport.

###### About Room
1. Rooms can be categorized according to its type: Single, Double, Deluxe, VIP Suite, etc. (you may
refer to hotel website for the types). Each type is at different rate.
2. Rooms have details like room number, bed type (single/double/master, WIFI enabled, facing (with
view), smoking/non-smoking, status, etc.
3. Room availability/details can be checked by entering the room number (id)/guest name.
4. Room availability status can be Vacant, Occupied, Reserved, Under Maintenance.

###### About Reservation
1. When a room is reserved, it will have a corresponding reservation details.
2. Reservations can be added with a reservation code/number, the associated guest details, room and
billing information (eg credit card), date of check in/out, number of adults/children, reservation
status.
3. An acknowledgement receipts with the essential reservation details should be provided (displayed)
when a reservation is made.
4. Reservations can be in different status: confirmed, in waitlist, checked-in, expired, etc. Note that
payment is not necessary at the time of reservation.
5. A reservation will be expired if no one checks in by a specific time point (example 1 hour after
scheduled check-in time), and the room should be made available again.
6. When guests check-in, the reservation and room/s should be updated and reflect the correct status.

###### About Room Service
1. Hotel staff can order room service meals on guest’s behalf upon his/her request.
2. List of menu items selection will be displayed for selection.
3. Each menu item will have a name, a description of how it is prepared and price.
4. When ordered, there will be a room service order created with a date/time, remarks (eg, less oil,
less salt) and the status (confirmed, preparing, delivered).
5. The order status will be updated accordingly.

###### About Payment
1. When a guest check-out, the total bill will be presented for payment. After payment, the room/s
will become available.
2. Total bill include room charges, tax, room services. Room charges can be different for weekends
and weekdays. In addition, discount could be provided for promotion.
3. Payment can be made in cash, credit card with details such as billing address, etc.
4. Room occupancy report can be generated based on the percentage of occupied rooms in a
particular day

###### Functional Requirements:
1. Create/Update/Search guests detail (Search by name using keyword/s)
2. Create/Update/Remove/Print reservation
3. Create/Update rooms details (include setting status like ‘Under Maintenance”,
4. Entering room service orders - list menu items for selection
5. Create/Update/Remove room service menu items.
6. Check room availability
7. Room Check-in (for walk-in or reservation)
8. Room Check-out and print bill invoice (with breakdowns on days of stay, room service order items
and its total, tax and total amount)
9. Print Room Status statistic report by

	1. Room type occupancy rate (room status = vacant) - (room type, number and list the room number).
    
    Single : Number : 10 out of 20 Rooms : 02-03, 03-04, 03-05,……. 
    
    Double : Number : 5 out of 10 Rooms : 02-04, 05-04, 05-05,…….

   	2. Room status (status type and list the room number)
    
    Vacant : Rooms : 02-03, 03-04, 03-05,…….
    
    Occupied : Rooms : 02-04, 05-04, 05-05,…….
	
	
****

***Disclaimer:*** This repo is no longer maintained and was submitted as part of the coursework assignment for CE 2002
