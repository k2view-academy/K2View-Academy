# Retention Period APIs

## Entity Reservation - Reservation (Retention) Period

### Get the Retention Period Options





### Validate the Reservation Period

The maximum reservation period by a tester is set in the [tdm_general_parameters](/articles/TDM/tdm_configuration/02_tdmdb_general_parameters.md) TDM DB table. The following param_value sets the maximum number of days of the reservation period: MAX_RESERVATION_DAYS_FOR_TESTER. The validation verifies that the number of days of the reservation period does not exceed the maximum allowed for testers.

Note that an admin and the environment owner users can define unlimited reservation period for entities by populating the number of days in the Retention Period setting with zero.

### API URL

/validateReservationPeriod

### HTTP Method

GET

### API Category

TDM_ReserveEntities

### API Description

TO check with Taha- how does it validate a list of entities on env (when the user extends the reservation period)? Maybe as need to have an optional parameter with the entity list + env + BE.



### API Input

- 

wsValiateReserveRetentionPeriod
