

-- INSERIR ADMIN AO RODAR O PROJETO
INSERT INTO users (active, name, email, password, rule, registration_date)
VALUES (true,
        'Admin',
        'admin@admin.com',
        '$2a$10$pZ7SXcJSoZR2.tBRL.0UeOG4JRo9i3YjDvJgLmP1O6CUi4pZwcGkm',
        'ADMIN', now()
       )
ON CONFLICT (name) DO NOTHING;