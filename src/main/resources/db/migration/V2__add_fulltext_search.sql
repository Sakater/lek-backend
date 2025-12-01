-- Füge search_vector Spalte hinzu (automatisch aktualisiert)
ALTER TABLE tasks ADD COLUMN search_vector tsvector
    GENERATED ALWAYS AS (
        setweight(to_tsvector('german', coalesce(question, '')), 'A') ||
        setweight(to_tsvector('german', coalesce(topic, '')), 'B') ||
        setweight(to_tsvector('german', coalesce(hint, '')), 'C')
        ) STORED;

-- GIN Index für schnelle Suche
CREATE INDEX idx_search_vector ON tasks USING GIN(search_vector);

-- Performance-Indizes (falls nicht durch @Table erstellt)
CREATE INDEX IF NOT EXISTS idx_subject_grade ON tasks(subject, grade);
CREATE INDEX IF NOT EXISTS idx_approved ON tasks(approved) WHERE approved = true;
CREATE INDEX IF NOT EXISTS idx_created_by ON tasks(created_by);
CREATE INDEX IF NOT EXISTS idx_type ON tasks(type);
