PGDMP     &                    y            market    13.2    13.1     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    24674    market    DATABASE     c   CREATE DATABASE market WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';
    DROP DATABASE market;
                postgres    false            �            1259    24677    person    TABLE     �   CREATE TABLE public.person (
    id integer NOT NULL,
    name character varying(128),
    role_id uuid,
    login character varying(128),
    password character varying(128)
);
    DROP TABLE public.person;
       public         heap    postgres    false            �            1259    24675    person_id_seq    SEQUENCE     �   CREATE SEQUENCE public.person_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.person_id_seq;
       public          postgres    false    201            �           0    0    person_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.person_id_seq OWNED BY public.person.id;
          public          postgres    false    200            �            1259    24687    product    TABLE     n   CREATE TABLE public.product (
    id integer NOT NULL,
    title character varying(128),
    price integer
);
    DROP TABLE public.product;
       public         heap    postgres    false            �            1259    24685    product_id_seq    SEQUENCE     �   CREATE SEQUENCE public.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.product_id_seq;
       public          postgres    false    203            �           0    0    product_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;
          public          postgres    false    202            �            1259    24693    role    TABLE     T   CREATE TABLE public.role (
    id uuid NOT NULL,
    name character varying(128)
);
    DROP TABLE public.role;
       public         heap    postgres    false            ,           2604    24680 	   person id    DEFAULT     f   ALTER TABLE ONLY public.person ALTER COLUMN id SET DEFAULT nextval('public.person_id_seq'::regclass);
 8   ALTER TABLE public.person ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    200    201    201            -           2604    24690 
   product id    DEFAULT     h   ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);
 9   ALTER TABLE public.product ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    202    203    203            �          0    24677    person 
   TABLE DATA           D   COPY public.person (id, name, role_id, login, password) FROM stdin;
    public          postgres    false    201   �       �          0    24687    product 
   TABLE DATA           3   COPY public.product (id, title, price) FROM stdin;
    public          postgres    false    203   @       �          0    24693    role 
   TABLE DATA           (   COPY public.role (id, name) FROM stdin;
    public          postgres    false    204   �       �           0    0    person_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.person_id_seq', 5, true);
          public          postgres    false    200            �           0    0    product_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.product_id_seq', 12, true);
          public          postgres    false    202            /           2606    24682    person person_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.person DROP CONSTRAINT person_pkey;
       public            postgres    false    201            1           2606    24692    product product_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.product DROP CONSTRAINT product_pkey;
       public            postgres    false    203            3           2606    24697    role role_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.role DROP CONSTRAINT role_pkey;
       public            postgres    false    204            4           2606    24698    person person_role_id_fkey    FK CONSTRAINT     x   ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.role(id);
 D   ALTER TABLE ONLY public.person DROP CONSTRAINT person_role_id_fkey;
       public          postgres    false    2867    201    204            �   z   x�E�1�0F����IDo��c'��%�\J� ��wm+��ރ��Y4�`���z����m�S,Gk��d�Jb�xk���nXÅ�ƱsDTO�okk��"#�v�n��u���"��~+      �   i   x�-�A
�0��F����[w�ph@IjJZ�o(�aF1��	�����P�"l1�`��"��h1�)BJ�/�v��>�=����	U��J50\�r�6      �   <   x�KN�0���4�5��4�5124ҵ07��M5MMI466
�p����;��z�q��qqq Rh*     