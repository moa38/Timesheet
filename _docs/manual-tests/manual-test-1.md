
# Manual Test 1 – Password Feature

 #### User Story:

> As a user. I want my account to be password protected. So my account is secure.

| Test Action | Expected Result | Actual Result | Pass / Fail |
|--|--|--|--|
| Pressed sign-in without entering any Error message | Error message "wrong log-in" | Error message "wrong log-in" | Pass |
| Pressed sign-in without entering a username, using an invalid password | Error message "wrong log-in" | Error message "wrong log-in"  | Pass |
| Pressed sign-in without entering a username | Error message "wrong log-in" | Error message "wrong log-in" | Pass |
| Pressed sign-in without entering a password | Error message "wrong log-in" | Error message "wrong log-in" | Pass |
| Pressed sign-in using an invalid username | Error message "wrong log-in" | Error message "wrong log-in" | Pass |
| Pressed sign-in using a valid username | Error message "wrong log-in" | Error message "wrong log-in" | Pass |
| Pressed sign-in using a valid username ad password | Successful login | Successful login | Pass |

