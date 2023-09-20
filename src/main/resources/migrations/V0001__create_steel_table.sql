-- Create the 'steels' table
CREATE TABLE steels (
                        id UUID PRIMARY KEY,
                        name VARCHAR(255),
                        manufacturer VARCHAR(255),
                        data_sheet VARCHAR(255),
                        history TEXT,
                        description TEXT,
                        edge_retention INT,
                        stainless INT,
                        toughness INT,
                        sharpening INT,
                        particle_metallurgy BOOLEAN
);

-- Create a table for the linked entries
CREATE TABLE external_links (
                                linked_id UUID PRIMARY KEY,
                                steels_id UUID REFERENCES steels(id),
                                link_value VARCHAR(255),
                                is_active BOOLEAN DEFAULT TRUE
);

-- Add an index on the `steels_id` column in the `external_links` table
CREATE INDEX idx_external_links_steels_id ON external_links (steels_id);