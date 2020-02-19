#Price Tracker(Android)
The app is functional for 3 tasks. 
First is Fetching the value of cryptocurrency from an given API and displaying the value by using mpAndroidChart which will show transaction history of the price over time.
Second using Recycler view displaying two tables side by side for the order book. Each table value is fetched by the given API.
Third setting the price alert onto the input given by user and comparing that price by hitting API every hour to the current price. If the price are lower then then user will get notification which would open the app directly when clicked.Used FirebaseJob dispatcher to implement it.
