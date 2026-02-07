
INSERT INTO insect_order (name, latin_name, description)
VALUES ('Chrząszcze', 'Coleoptera', 'Rząd owadów charakteryzujący się twardymi pokrywami.');
INSERT INTO insect_order (name, latin_name, description)
VALUES ('Motyle', 'Lepidoptera', 'Owady o skrzydłach pokrytych barwnymi łuskami.');


INSERT INTO insect_family (name, latin_name)
VALUES ('Biegaczowate', 'Carabidae');
INSERT INTO insect_family (name, latin_name)
VALUES ('Paziowate', 'Papilionidae');


INSERT INTO habitat (name, type, climate_description)
VALUES ('Las liściasty', 'Leśny', 'Umiarkowany, duża wilgotność.');
INSERT INTO tag (name)
VALUES ('Drapieżnik');
INSERT INTO tag (name)
VALUES ('Chroniony');


INSERT INTO insect (common_name, latin_name, description, order_id, family_id, is_protected, danger_level)
VALUES ('Biegacz fioletowy', 'Carabus violaceus', 'Nocny drapieżnik o fioletowym połysku.', 1, 1, true, 'HARMLESS');

INSERT INTO insect (common_name, latin_name, description, order_id, family_id, is_protected, danger_level)
VALUES ('Paź królowej', 'Papilio machaon', 'Jeden z najpiękniejszych polskich motyli.', 2, 2, false, 'HARMLESS');