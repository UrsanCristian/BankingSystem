# Banking system


## Functionality

- User system
- Bank accounts
- Transactions
- Loans
- Admin system
- ...


## Structure

- User
  - Name
  - Email
  - Password
  - Phone number
  - Address
  - Date of birth
  - List of bank accounts --> 1 to many

- Bank
  - Name
  - Address
  - Phone number
  - Email
  - List of Bank Acounts --> 1 to many

- Bank account
    - Account number
    - Balance
    - Currency
    - List of transactions --> 1 to many
    - List of loans --> 1 to many

- Transaction
    - Amount
    - Date
    - Description
    - Sender account
    - Receiver account

- Loan
    - Amount
    - Interest rate
    - Duration
    - Date
    - Status
    - List of payments --> 1 to many








