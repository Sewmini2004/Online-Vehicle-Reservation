USE vehicle_reservation;

-- Create the User Table
CREATE TABLE User (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100),
    password VARCHAR(100)
);
-- Create the Customer Table
CREATE TABLE Customer (
    customerId INT AUTO_INCREMENT PRIMARY KEY,
    userId INT,
    name VARCHAR(255),
    address VARCHAR(255),
    nic VARCHAR(20),
    phoneNumber VARCHAR(15),
    registrationDate DATE,
    email VARCHAR(255),
    FOREIGN KEY (userId) REFERENCES User(userId)  -- Assuming you have a User table
);

-- Create the Vehicle Table
CREATE TABLE Vehicle (
    vehicleId INT AUTO_INCREMENT PRIMARY KEY,
    carType VARCHAR(50),
    model VARCHAR(50),
    availabilityStatus VARCHAR(20),
    registrationNumber VARCHAR(50),
    fuelType VARCHAR(20),
    carModel VARCHAR(50),
    seatingCapacity INT
);


-- Create the Driver Table
CREATE TABLE Driver (
    driverId INT AUTO_INCREMENT PRIMARY KEY,
    vehicleId INT,
    name VARCHAR(255),
    licenseNumber VARCHAR(50),
    status VARCHAR(50),
    shiftTiming VARCHAR(100),
    salary DECIMAL(10, 2),
    experienceYears INT,
    phoneNumber VARCHAR(15),
    FOREIGN KEY (vehicleId) REFERENCES Vehicle(vehicleId)  -- Assuming you have a Vehicle table
);


-- Create the Booking Table
CREATE TABLE Booking (
    bookingId INT AUTO_INCREMENT PRIMARY KEY,
    customerId INT,
    vehicleId INT,
    driverID INT,
    pickupLocation VARCHAR(255),
    dropLocation VARCHAR(255),
    bookingDate DATE,
    carType VARCHAR(50),
    totalBill DECIMAL(10, 2),
    FOREIGN KEY (customerId) REFERENCES Customer(customerId),
    FOREIGN KEY (vehicleId) REFERENCES Vehicle(vehicleId),
    FOREIGN KEY (driverID) REFERENCES Driver(driverId)
);

-- Create the Billing Table
CREATE TABLE Billing (
    billId INT AUTO_INCREMENT PRIMARY KEY,
    bookingId INT,
    totalAmount DECIMAL(10, 2),
    discountAmount DECIMAL(10, 2),
    taxAmount DECIMAL(10, 2),
    finalAmount DECIMAL(10, 2),
    paymentMethod VARCHAR(50),
    paymentStatus VARCHAR(20),
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (bookingId) REFERENCES Booking(bookingId)
);


-- Create the Notification Table
CREATE TABLE Notification (
    notificationId INT AUTO_INCREMENT PRIMARY KEY,
    userId INT,
    message TEXT,
    status VARCHAR(50),
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (userId) REFERENCES User(userId)
);

