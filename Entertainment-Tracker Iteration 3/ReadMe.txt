ReadMe File for COMP 3350 Project

Packages


-application : It contains the classes Main and Service. Main class starts up the application by calling Services class and Service class creates the persistent database. Also, Main closes down the application by calling a method in the Services class



-business : AccessItem allows presentation to access any type of item (Book, Move, and TV show), the watched history, and the wish list. The Sorter class helps sort out items in a list. Finally, the ItemSearch class is used for searching through the system for specific items or items with specific words in them.



-objects : The EntertainmentItem represents a movie, book, or TV show in the system. They have various data attached to them, such as name, description, average rating, and user rating.



-persistence : HSQLDB is used for the application. Also, the original stub and HSQLDB can be switched by dependency injection thanks to their shared DataAccess interface.



-presentation : New additions are SplashActivity (a splash screen that shows up when launching the app), WishListActivity (a list for the user to keep track of what they plan to see/read in the future), and InformationActivity (the pop up for when you click on an item. It shows various information, like description and rating, of the item you clicked on).


The log file is contained in the same directory level as this read me file.

Major changes in Iteration 3

Every item can be clicked in any activity to pop up an information box with a larger version of the image, a description of the item, and its user and average ratings. The average rating just being some baked in value. 
Additionally, there is now a wish list, so users can use the app to both keep track of what they've seen/read and what they would like to see/read in the future. Adding an item to the watched history will automatically remove it from the wish list if necessary.
WatchedList has been renamed across the board to "Watched History"
A splash screen has been added to the app while it loads.
The default ordering of retrieving items from the database has been changed to be alphabetical.
Finally, instead of the dialog box of the previous iteration, every item has two small buttons. The bookmark will add the item to the wish list and the eye will add the item to the watched history.