Feature: Validate the ecommerce buy functionality

Scenario: Navigate to buy page

Given Application is launched
When User get temperature value
And if temp is less than 19 click on buymoisterizer or temp greater than 34 click on buysunscreen
Then Click on PaywithCard btn






  