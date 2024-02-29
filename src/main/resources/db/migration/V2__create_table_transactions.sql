CREATE TABLE transactions (
    id UUID PRIMARY KEY,
    amount DECIMAL(19, 2),
    sender_id UUID,
    receiver_id UUID,
    created_at TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES users(id),
    FOREIGN KEY (receiver_id) REFERENCES users(id)
);