#Build Your Project
For this project, you will be creating a book listing app. A user should be able to enter a keyword, press the search button, and recieve a list of published books which relate to that keyword.

To achieve this, you'll make use of the Google Books API. This is a well-maintained API which returns information in a JSON format.

We suggest first exploring the API and learning what information it returns given a particular query. An example query that we found useful was

  https://www.googleapis.com/books/v1/volumes?q=android&maxResults=1

Once you've explored the API, begin work in Android Studio. You'll want a simple layout initially, with an editable TextView and a 'search' button.

Then, you'll want to build the AsyncTask that queries the API. This is a complex step, so be sure to reference the course materials when needed.

Once you've queried the API, you'll need to parse the result. This will involve storing the information returned by the API in a custom class.

Finally, you'll use the List and Adapter pattern to populate a list on the user's screen with the information stored in the custom objects you wrote earlier.

Your project will be evaluated using the Book Listing project rubric.

##Additional Criteria
The intent of this project is to give you practice writing raw Java code using the necessary classes provided by the Android framework; therefore, the use of external libraries will not be permitted to complete this project.