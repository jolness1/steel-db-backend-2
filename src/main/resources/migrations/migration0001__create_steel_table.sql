-- Create the 'steels' table
CREATE TABLE steels (
                        id UUID PRIMARY KEY,
                        name VARCHAR(255),
                        manufacturer VARCHAR(255),
                        data_sheet VARCHAR(255)
                        history TEXT
);

-- Create a table for the linked entries (assuming it's named 'linked_entries')
CREATE TABLE external_links (
                                linked_id UUID PRIMARY KEY,
                                steels_id INT REFERENCES steels(id),
                                link_value VARCHAR(255)
);