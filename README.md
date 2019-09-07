# C195 Scheduling Application

## Features
* Has a log-in form that can determine the user’s location and translate log-in and error control messages into two languages (English and Spanish)
* Provides the user the ability ability to add, update, and delete customer records in the database, including name, address, and phone number.
* Provides the user the ability ability to add, update, and delete appointments, capturing the type of appointment and a link to the specific customer record in the database.
* Provides the user the ability to view a calendar with appointments stored in a disconnected database by month and by week.
* Automatically adjusts appointment times based on user time zones and daylight saving time.
* Contains exception controls to prevent scheduling an appointment outside business hours, scheduling overlapping appointments,entering nonexistent or invalid customer data, and entering an incorrect username and password.
* Provides an alert if there is an appointment within 15 minutes of the user’s log-in.
* Generates reports that detail the number of appointment types by month, the schedule for each consultant, the total number of oppointments for each customer.
* Automatically records successful and failed login attemps in a .txt file which can be accessed from the main controller.

## Usage
I've removed my credentials which are needed to log into the database provided to me by WGU so you will need to add information to util.Database to address the database you're trying to connect to. Only after doing so can you launch the program.
