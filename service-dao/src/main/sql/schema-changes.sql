-- ============================================================================
-- File for SQL that will change the schema.  Clear this file after prod deploy.
-- ============================================================================
alter type site_status_type add value 'VOTING';
alter type site_status_type add value 'PLAN';
alter type site_status_type add value 'EXPANDING';

alter table site 
    add column stalls_urban     int4                           null,
    add column stalls_v2        int4                           null,
    add column stalls_v3        int4                           null,
    add column stalls_v4        int4                           null,
    add column stalls_trailer   int4                           null,
    add column plugs_tesla_us   int4                           null,
    add column plugs_type2      int4                           null,
    add column plugs_type2_ccs2 int4                           null,
    add column plugs_ccs2       int4                           null,
    add column plugs_gbt_china  int4                           null,
    add column plugs_nacs       int4                           null,
    add column plugs_magicdock  int4                           null,
    add column paid_parking     bool                           null,
    add column facility_name    varchar(200)                   null     default null::character varying,
    add column facility_hours   varchar(100)                   null     default null::character varying,
    add column access_notes     varchar(1000)                  null     default null::character varying,
    add column address_notes    varchar(1000)                  null     default null::character varying,
    add column plugshare_id     int8                           null,
    add column osm_id           int8                           null;
    