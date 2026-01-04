# Bookstore App
This project is a Java application built in NetBeans that simulates a basic online bookstore. Customers can purchase books using money or redeem accumulated loyalty points earned from previous purchases.

The system tracks customer points, customer status (Silver or Gold), and book purchase or redemption activity. Customer status is managed using the State Design Pattern, allowing status transitions to occur automatically at runtime.

Customers remain in Silver status while they have fewer than 1000 points and transition to Gold status once they reach 1000 points or more. This design avoids complex conditional logic and makes the application easier to maintain and extend.
