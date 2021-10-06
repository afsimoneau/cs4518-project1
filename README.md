# cs4518-projects

## Grading

One issue with our project which was not able to be addressed before submission was the API returning a city that was not valid for our request. I was not able to identify why this occurred in the request, but we are getting responses from the API with only querying latitude and longitude. My theory is that the lat/lon is not being updated in the ScoreFragment before the API call. 