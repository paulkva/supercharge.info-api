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
    add column plugs_tpc        int4                           null,
    add column plugs_nacs       int4                           null,
    add column plugs_magicdock  int4                           null,
    add column plugs_gbt_china  int4                           null,
    add column plugs_type2      int4                           null,
    add column plugs_ccs2       int4                           null,
    add column plugs_ccs2_type2 int4                           null,
    add column plugs_ccs2_tpc   int4                           null,
    add column paid_parking     bool                           null,
    add column facility_name    varchar(200)                   null     default null::character varying,
    add column facility_hours   varchar(100)                   null     default null::character varying,
    add column access_notes     varchar(1000)                  null     default null::character varying,
    add column address_notes    varchar(1000)                  null     default null::character varying,
    add column plugshare_id     int8                           null,
    add column osm_id           int8                           null;

alter table country
    add column plugs_tpc        boolean      not null default true,
    add column plugs_nacs       boolean      not null default true,
    add column plugs_magicdock  boolean      not null default true,
    add column plugs_gbt_china  boolean      not null default true,
    add column plugs_type2      boolean      not null default true,
    add column plugs_ccs2       boolean      not null default true,
    add column plugs_ccs2_type2 boolean      not null default true,
    add column plugs_ccs2_tpc   boolean      not null default true;

-- pre-populate valid plug types per country
UPDATE country SET plugs_gbt_china = false WHERE country_id != (SELECT country_id WHERE name = 'China');
UPDATE country SET plugs_tpc = false, plugs_magicdock = false, plugs_nacs = false WHERE region_id = 101; -- EU
UPDATE country SET plugs_type2 = false, plugs_ccs2 = false, plugs_ccs2_type2 = false, plugs_ccs2_tpc = false WHERE region_id = 100; -- NA

-- Pre-populate stalls based on max power
UPDATE site SET stalls_urban = stall_count WHERE power_kwatt <= 72;
UPDATE site SET stalls_v2 = stall_count WHERE power_kwatt BETWEEN 73 AND 199;
UPDATE site SET stalls_v3 = stall_count WHERE power_kwatt >= 200; -- a few of these are V4 at the time of release and will have to be updated manually

-- Pre-populate plugs based on geography, max power, and existing other_evs flag

-- All stalls in China are GB/T
UPDATE site s SET plugs_gbt_china = stall_count
FROM address a, country c
WHERE s.address_id = a.address_id AND a.country_id = c.country_id
AND c.name = 'China';

-- All stalls in Jordan are Type2
UPDATE site s SET plugs_type2 = stall_count
FROM address a, country c
WHERE s.address_id = a.address_id AND a.country_id = c.country_id
AND c.name = 'Jordan';

-- Taiwan is mostly dual-cable CCS2+TPC but some will have to be updated manually
UPDATE site s SET plugs_ccs2_tpc = s.stall_count
FROM address a, country c
WHERE s.address_id = a.address_id AND a.country_id = c.country_id
AND c.name = 'Taiwan';

-- North America V2 + Urban, and all stalls in Japan + South Korea, are TPC if they're not already marked open to other EVs
UPDATE site s SET plugs_tpc = s.stall_count
FROM address a, country c
WHERE s.address_id = a.address_id AND a.country_id = c.country_id
AND ((s.power_kwatt < 200 AND c.region_id = 100) OR c.name IN ('Japan', 'South Korea')) AND NOT s.other_evs;

-- North America + South Korea stalls that are already marked open to other EVs are all MagicDock
UPDATE site s SET plugs_magicdock = s.stall_count
FROM address a, country c
WHERE s.address_id = a.address_id AND a.country_id = c.country_id
AND (c.region_id = 100 OR c.name = 'South Korea') AND s.other_evs;

-- North America V3 + V4 stalls are all NACS if they're not already marked open to other EVs
UPDATE site s SET plugs_nacs = s.stall_count
FROM address a, country c
WHERE s.address_id = a.address_id AND a.country_id = c.country_id
AND c.region_id = 100 AND s.power_kwatt = 250 AND NOT s.other_evs;

-- Presume the rest of the world is CCS2
UPDATE site s SET plugs_ccs2 = stall_count
FROM address a, country c
WHERE s.address_id = a.address_id AND a.country_id = c.country_id
AND c.region_id != 100 AND c.name NOT IN ('China', 'Japan', 'Jordan', 'South Korea', 'Taiwan');
