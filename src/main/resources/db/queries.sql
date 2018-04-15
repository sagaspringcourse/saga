Hibernate:
/* select
    p
from
    Player p */
SELECT
  player0_.ID         AS ID1_4_,
  player0_.ADDRESS    AS ADDRESS2_4_,
  player0_.AGE        AS AGE3_4_,
  player0_.EMAIL      AS EMAIL4_4_,
  player0_.FIRST_NAME AS FIRST_NA5_4_,
  player0_.LAST_NAME  AS LAST_NAM6_4_,
  player0_.TEAM_ID    AS TEAM_ID7_4_
FROM
  s_player player0_

Hibernate:
/*
from
Player p
where
(
    p.age between :ageMin and :ageMax
)
and firstName like '%ik%'
order by
p.firstName */
SELECT
  player0_.ID         AS ID1_4_,
  player0_.ADDRESS    AS ADDRESS2_4_,
  player0_.AGE        AS AGE3_4_,
  player0_.EMAIL      AS EMAIL4_4_,
  player0_.FIRST_NAME AS FIRST_NA5_4_,
  player0_.LAST_NAME  AS LAST_NAM6_4_,
  player0_.TEAM_ID    AS TEAM_ID7_4_
FROM
  s_player player0_
WHERE
  (
    player0_.AGE BETWEEN ? AND ?
  )
  AND (
    player0_.FIRST_NAME LIKE '%ik%'
  )
ORDER BY
  player0_.FIRST_NAME
