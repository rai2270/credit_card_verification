# credit_card_verification

MainActivity uses the arch component navigation to add one fragment -> MainFragment.

The main parts in 3 classes:
1) MainFragment:
-detect card type while the user enter a number. Set all related fields for all ui elements:
max sizes and hints that are related to the detected card type.
-detect date after user enter a date, and set all related fields for all ui elements
Submit button:
-run validation tests on the card number
-hide keyboard while app try to validate card
-Basic validation test before continue to try and validate card:
-validate min length for the card
-validate date exists
-validate security length

2) Card:
validate using Luhn algorithm: I've used: https://www.rosettacode.org/wiki/Luhn_test_of_credit_card_numbers#Java

3) CardTypes:
Detect card types based on:
http://en.wikipedia.org/wiki/Bank_card_number
To create regex for each card: I've used: http://gamon.webfactional.com/regexnumericrangegenerator/

JUnits tests (run from android studio) :
--------------------------------------------
Location: test\java\com\example\card\cardapplication
CardTypeSecurityLengthTest.java
CardTypeTest.java
CardValidateTest.java
