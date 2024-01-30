INSERT INTO "banka"("id", "naziv", "kontakt", "pib")
VALUES('-100', 'Test', '123', '123');
INSERT INTO "banka"("id", "naziv", "kontakt", "pib")
VALUES(nextval('banka_seq'), 'UniCredit Bank', '+381 11 30 50 505', '12289564');
INSERT INTO "banka"("id", "naziv", "kontakt", "pib")
VALUES(nextval('banka_seq'), 'Erste Bank', '+381 33 34 50 885', '98547862');
INSERT INTO "banka"("id", "naziv", "kontakt", "pib")
VALUES(nextval('banka_seq'), 'Addiko Bank', '+381 76 98 50 987', '57436158');
INSERT INTO "banka"("id", "naziv", "kontakt", "pib")
VALUES(nextval('banka_seq'), 'Banka Intesa', '+381 23 56 50 665', '75486251');


INSERT INTO "filijala"("id", "adresa", "broj_pultova", "poseduje_sef", "banka")
VALUES('-100', 'Test', 123 , true, 1);
INSERT INTO "filijala"("id", "adresa", "broj_pultova", "poseduje_sef", "banka")
VALUES(nextval('filijala_seq'), 'Bulevar oslobodenja 383, 11040 Beograd, Srbija', 8 , true, 1);
INSERT INTO "filijala"("id", "adresa", "broj_pultova", "poseduje_sef", "banka")
VALUES(nextval('filijala_seq'), 'Justina Popovica 3, 11080 Zemun, Beograd', 7 , false, 3);
INSERT INTO "filijala"("id", "adresa", "broj_pultova", "poseduje_sef", "banka")
VALUES(nextval('filijala_seq'), 'Bulevar oslobodenja 123, 11040 Beograd, Srbija', 10 , false, 4);
INSERT INTO "filijala"("id", "adresa", "broj_pultova", "poseduje_sef", "banka")
VALUES(nextval('filijala_seq'), 'DOBANOVACKI PUT B.B. 11271, SURCIN', 6 , true, 1);


INSERT INTO "korisnik_usluge"("id", "ime", "prezime", "maticni_broj")
VALUES('-100', 'Test', 'Test' , '123');
INSERT INTO "korisnik_usluge"("id", "ime", "prezime", "maticni_broj")
VALUES(nextval('korisnik_usluge_seq'), 'Ana', 'Jovanovic' , '345623');
INSERT INTO "korisnik_usluge"("id", "ime", "prezime", "maticni_broj")
VALUES(nextval('korisnik_usluge_seq'), 'Jovan', 'Popovic' , '489567');
INSERT INTO "korisnik_usluge"("id", "ime", "prezime", "maticni_broj")
VALUES(nextval('korisnik_usluge_seq'), 'Jelena', 'Jovanovic' , '784592');
INSERT INTO "korisnik_usluge"("id", "ime", "prezime", "maticni_broj")
VALUES(nextval('korisnik_usluge_seq'), 'Nemanja', 'Novakovic' , '746982');



INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "filijala", "korisnik_usluge")
VALUES('-100', 'Test', 'Test' , to_date('01.03.2023.', 'dd.mm.yyyy.'), 23, 1, 1);
INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "filijala", "korisnik_usluge")
VALUES(nextval('usluga_seq'), 'dinarska stednja', 'Orocena dinarska stednja, beybedna i sigurna ustedjevina' , to_date('01.03.2023.', 'dd.mm.yyyy.'), 23, 1, 4);
INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "filijala", "korisnik_usluge")
VALUES(nextval('usluga_seq'), 'elektronsko bankarstvo', 'Internet ponuda' , to_date('05.05.2023.', 'dd.mm.yyyy.'), 12, 4, 1);
INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "filijala", "korisnik_usluge")
VALUES(nextval('usluga_seq'), 'otvaranje racuna', 'Novi korisnik - potpisivanje ugovora i otvaranje racuna' , to_date('07.05.2023.', 'dd.mm.yyyy.'), 21, 3, 3);
INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "filijala", "korisnik_usluge")
VALUES(nextval('usluga_seq'), 'osiguranje', 'Osiguranje' , to_date('05.05.2023.', 'dd.mm.yyyy.'), 10, 2, 2);