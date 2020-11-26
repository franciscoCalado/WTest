# WTest

This app consumes a REST API to show a list of items. Clicking said items will navigate to a detailed view. There is also a FAB at the bottom that brings up an hypothetical "Send message" form. All field are validated but the message is sent nowhere. 

The app uses:
- MVP architecture;
- RxJava2;
- Retrofit to handle network requests;
- Glide to handle network image loading;
- Room for database;

Run Instructions:

Just clone the project into android studio and choose the build variant you want to try (Full or Lite). From there it should just be a matter of building the project and everything will be ready to go;

Missing features:
- Mapping the downloaded CSV file into room; There's a more detailed explanation in the code as to why;
- Querying the database and showing the results;
- Implementing Dagger (dependencies are being injected and their life cycle is being managed but in a very inefficient way);
- Unit and integration tests;
