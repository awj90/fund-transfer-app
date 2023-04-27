## Basic Spring-boot Web Application that queries account information from mySQL and logs transaction information to Redis.

## GET /

Landing page for fund transfer

- Fund transfers cannot be between same accounts
- Source account must have sufficient funds for transfer amount
- Minimum fund transfer amount is $10

## POST /transfer

If fund transfer was successful:

- Render confirmation page with transaction details

If fund transfer was unsuccessful:

- Stays on landing page with errors displayed
