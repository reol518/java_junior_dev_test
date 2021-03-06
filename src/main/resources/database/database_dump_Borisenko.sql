PGDMP         :                x            TEST_database    12.4    12.4                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16393    TEST_database    DATABASE     �   CREATE DATABASE "TEST_database" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';
    DROP DATABASE "TEST_database";
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                postgres    false                       0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   postgres    false    3            �            1259    16440 	   customers    TABLE     �   CREATE TABLE public.customers (
    id integer NOT NULL,
    first_name character varying NOT NULL,
    last_name character varying NOT NULL
);
    DROP TABLE public.customers;
       public         heap    postgres    false    3            �            1259    16438    customers_id_seq    SEQUENCE     �   CREATE SEQUENCE public.customers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.customers_id_seq;
       public          postgres    false    203    3                       0    0    customers_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.customers_id_seq OWNED BY public.customers.id;
          public          postgres    false    202            �            1259    16460    orders    TABLE     c   CREATE TABLE public.orders (
    customer_id integer,
    product_id integer,
    buy_date date
);
    DROP TABLE public.orders;
       public         heap    postgres    false    3            �            1259    16451    products    TABLE     u   CREATE TABLE public.products (
    id integer NOT NULL,
    product character varying NOT NULL,
    price integer
);
    DROP TABLE public.products;
       public         heap    postgres    false    3            �            1259    16449    products_id_seq    SEQUENCE     �   CREATE SEQUENCE public.products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.products_id_seq;
       public          postgres    false    3    205                       0    0    products_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;
          public          postgres    false    204            �
           2604    16443    customers id    DEFAULT     l   ALTER TABLE ONLY public.customers ALTER COLUMN id SET DEFAULT nextval('public.customers_id_seq'::regclass);
 ;   ALTER TABLE public.customers ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    202    203    203            �
           2604    16454    products id    DEFAULT     j   ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);
 :   ALTER TABLE public.products ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    205    204    205                      0    16440 	   customers 
   TABLE DATA           >   COPY public.customers (id, first_name, last_name) FROM stdin;
    public          postgres    false    203   W                 0    16460    orders 
   TABLE DATA           C   COPY public.orders (customer_id, product_id, buy_date) FROM stdin;
    public          postgres    false    206                    0    16451    products 
   TABLE DATA           6   COPY public.products (id, product, price) FROM stdin;
    public          postgres    false    205   �                  0    0    customers_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.customers_id_seq', 8, true);
          public          postgres    false    202                        0    0    products_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.products_id_seq', 8, true);
          public          postgres    false    204            �
           2606    16448    customers customers_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.customers DROP CONSTRAINT customers_pkey;
       public            postgres    false    203            �
           2606    16459    products products_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.products DROP CONSTRAINT products_pkey;
       public            postgres    false    205            �
           2606    16463    orders orders_customer_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES public.customers(id);
 H   ALTER TABLE ONLY public.orders DROP CONSTRAINT orders_customer_id_fkey;
       public          postgres    false    203    2702    206            �
           2606    16468    orders orders_product_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.products(id);
 G   ALTER TABLE ONLY public.orders DROP CONSTRAINT orders_product_id_fkey;
       public          postgres    false    205    206    2704               �   x�U�]
�@�����{�Z�I�D/Pk�n�^ar#g	If���'T(��DT:\�la��~(���
�jD�>�(Y��l#8�V�����ױJ��Z��#�D���s#[��Eg�NI1��S����/���{��'��ho�m>�?�{��l/�         p   x�����0������lw��?G���O�<��@�F+@1*���[N�"��t��Ze��
G+G���&�I��T�	M�d՞ʆ�k�}��s�I\�2W�>���X:�         v   x��1
�@�z�0�w�M<��I'����DDqs��7�[L�at֢�?H����S+ra᪷ͪ*:c]��#��Xb�'�M����n>������x����q]}���xؑ��m=�     