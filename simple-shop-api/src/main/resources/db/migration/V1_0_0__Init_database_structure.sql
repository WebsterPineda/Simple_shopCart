create table `clientes` (
    `id` bigint auto_increment,
    `nombre` varchar(75) not null,
    `apellido` varchar(100) not null,
    `direccion` varchar(300) not null,
    constraint primary key (id)
);

create table `productos` (
    `id` bigint auto_increment,
    `descripcion` varchar(300) not null,
    `unidad_medida` varchar(75) not null,
    `precio` decimal(13,4) not null,
    constraint primary key (id)
);

create table `orden_compra_e`(
    `id` bigint auto_increment,
    `clienteId` bigint not null,
    `sell_date` datetime not null,
    `total` decimal(13,4) not null,
    constraint primary key (id),
    constraint `FK_Cli_OrdenComp_E` foreign key (`clienteId`) references `clientes`(`id`) on update restrict on delete restrict
);

create table `orden_compra_d` (
    `id` bigint auto_increment,
    `id_header` bigint not null,
    `id_producto` bigint not null,
    `qty` int not null,
    `unit_price` decimal(13,4) not null,
    `total` decimal(13,4) not null,
    constraint primary key (id),
    constraint `FK_OrdenCom-D_Prod` foreign key (`id_producto`) references `productos`(`id`) on update restrict on delete restrict,
    constraint `FK_OrdenComp-D_Enc` foreign key (`id_header`) references `orden_compra_e`(`id`) on update restrict on delete restrict
);