CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE beneficiary(
    id uuid DEFAULT uuid_generate_v4 () PRIMARY KEY,
    name VARCHAR(255) unique NOT NULL,
    create_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE account(
    id uuid DEFAULT uuid_generate_v4 () PRIMARY KEY,
    owner_id uuid references beneficiary(id) on delete cascade,
    number VARCHAR(255) NOT NULL,
    pincode VARCHAR(4) NOT NULL,
    balance BIGINT,
    create_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE transaction_history(
    id uuid DEFAULT uuid_generate_v4 () PRIMARY KEY,
    owner_id uuid references beneficiary(id) on delete cascade,
    operation VARCHAR(255) NOT NULL,
    source_account_id uuid references account(id) on delete cascade,
    destination_account_id uuid references account(id) on delete cascade,
    value BIGINT NOT NULL,
    date TIMESTAMP WITH TIME ZONE NOT NULL
);