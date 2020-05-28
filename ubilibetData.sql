INSERT INTO public.funcions VALUES (1, 'Edició Usuaris', 'Crear, modificar i eliminar usuaris');
INSERT INTO public.funcions VALUES (3, 'Control Usuaris', 'Marc usuaris');
INSERT INTO public.funcions VALUES (4, 'Control Departaments', 'Marc departaments');
INSERT INTO public.funcions VALUES (2, 'Edicio permisos usuaris i canvi departament', 'Assignar i revocar permisos usuaris');

INSERT INTO public.departaments VALUES (1, 'Seguretat');
INSERT INTO public.departaments VALUES (2, 'Administració');

INSERT INTO public.permisos_departaments VALUES (1, 1, 0);
INSERT INTO public.permisos_departaments VALUES (2, 1, 0);
INSERT INTO public.permisos_departaments VALUES (3, 2, 4);
INSERT INTO public.permisos_departaments VALUES (4, 2, 4);

INSERT INTO public.usuaris VALUES (1, 'Super usuari 1', 'tech@ubilibet.com', '1NouControl', false, 0, 2);

INSERT INTO public.permisos_usuaris VALUES (1, 1, 4, false);
INSERT INTO public.permisos_usuaris VALUES (2, 1, 4, false);
INSERT INTO public.permisos_usuaris VALUES (3, 1, 4, false);
INSERT INTO public.permisos_usuaris VALUES (4, 1, 4, false);


SELECT pg_catalog.setval('public.contactes_admin_tec_id_seq', 1, false);

SELECT pg_catalog.setval('public.contactes_fac_autoritzats_id_seq', 1, false);

SELECT pg_catalog.setval('public.contactes_fac_id_seq', 1, false);

SELECT pg_catalog.setval('public.departaments_id_seq', 2, true);

SELECT pg_catalog.setval('public.dns_id_seq', 1, false);

SELECT pg_catalog.setval('public.families_id_seq', 1, false);

SELECT pg_catalog.setval('public.funcions_id_seq', 4, true);

SELECT pg_catalog.setval('public.grups_id_seq', 1, false);

SELECT pg_catalog.setval('public.sectors_id_seq', 1, false);

SELECT pg_catalog.setval('public.tarifes_id_seq', 1, false);

SELECT pg_catalog.setval('public.titulars_id_seq', 1, false);

SELECT pg_catalog.setval('public.usuaris_id_seq', 1, true);


-- Completed on 2020-05-28 22:53:42 CEST

--
-- PostgreSQL database dump complete
--

