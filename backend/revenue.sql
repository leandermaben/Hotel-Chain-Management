create or replace procedure calcRevenue(logdate in date,revenue out number,profit out number) as
        accRecord accounts%rowtype;
        stRecord stay%rowtype;
        invRecord invoice%rowtype;
        rmRecord room%rowtype;
        foodCost invoice.price%type;
        roomCost number(10,2);
        basicCost room.basic_cost%type; 
        cursor acc is select * from accounts;
        cursor st is select * from stay;
    BEGIN
        foodCost:=0;
        roomCost:=0;
        FOR invRecord in (select * from invoice where issued like logdate)
            LOOP
                foodCost:=foodCost+invRecord.price;
            END LOOP;
        FOR stRecord in (select * from stay where check_out like logdate)
            LOOP
                select basic_cost into basicCost from room where room_num=stRecord.room_num and branch_id=stRecord.branch_id; 
                roomCost:=roomCost+basicCost*(stRecord.check_out-stRecord.check_in)+stRecord.extra_cost-stRecord.discount;
            END LOOP;
        select * into accRecord from accounts where log_date like logdate;
        revenue:=roomCost+foodCost;
        profit:=roomCost+foodCost-accRecord.wages-accRecord.kitchen-accRecord.taxes-accRecord.bills-accRecord.other;
    END;

create or replace procedure addAccountRecord(logdate in date,wages out number) AS
    accRecord accounts%rowtype;
    num number(1);
    BEGIN
        select count(*) into num from accounts where log_date like logdate;
        IF num = 1 THEN
            select wages into wages from accounts where log_date like logdate;
            delete from accounts where log_date like logdate;
        ELSE
            select salary/30 into wages from employee where released is null;
        END IF;
    END;



create or replace trigger addAccount  before insert on accounts
    FOR EACH ROW
    BEGIN
       addAccountRecord(:NEW.log_date,:NEW.wages);
    END;

