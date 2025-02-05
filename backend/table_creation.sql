alter session set NLS_DATE_FORMAT='YYYY-MM-DD';
create table branch(branch_id varchar(10) primary key,
                    street varchar(100),
                    city varchar(100),
                    gm_id varchar(10),
                    phone varchar(20),
                    branch_status varchar(10));
create table employee(emp_id varchar(10) primary key, 
                    fname varchar(100), 
                    lname varchar(100), 
                    gender varchar(1),
                    position varchar(100), 
                    joined date, 
                    released date, 
                    salary number(10,2),
                    aadhar_number varchar(50) unique, 
                    branch_id varchar(10),
                    foreign key(branch_id) references branch(branch_id),
                    check(gender in('M','F')));
alter table branch add constraint gm_key foreign key(gm_id) references employee(emp_id) ; 
create table shift(shift_id varchar(10) primary key, 
                    duty varchar(100), 
                    slot_start timestamp, 
                    slot_end timestamp,
                    floor varchar(10));
create table room(branch_id varchar(10), 
                    room_num varchar(10), 
                    floor varchar(10), 
                    room_status varchar(50), 
                    serviced varchar(10), 
                    basic_cost number(10,2),
                    primary key(branch_id,room_num),
                    foreign key(branch_id) references branch(branch_id));
create table customer(customer_id varchar(10) primary key, 
                    fname varchar(100),
                    lname varchar(100), 
                    email varchar(200), 
                    aadhar_number varchar(50) unique, 
                    passport_number varchar(50) unique,
                    gender varchar(1),
                    check(gender in('M','F')));
create table stay(transaction_id varchar(10) primary key, 
                    customer_id varchar(10),
                    check_in date,
                    check_out date,
                    room_num varchar(10),
                    branch_id varchar(10),
                    extra_cost number(10,2),
                    discount number(5,2),
                    foreign key(customer_id) references customer(customer_id),
                    foreign key(room_num,branch_id) references room(room_num,branch_id));
create table invoice(bill_id varchar(10) primary key, 
                    issued date, 
                    price number(10,2), 
                    transaction_id varchar(10), 
                    customer_id varchar(10),
                    branch_id varchar(10),
                    foreign key(customer_id) references customer(customer_id) ,
                    foreign key(transaction_id) references stay(transaction_id),
                    foreign key(branch_id) references branch(branch_id));
create table food(item varchar(100) primary key, 
                    price number(10,2), 
                    sold number(25),
                    available varchar(1),
                    check(available in('y','n')));
create table booking(booking_id varchar(10) primary key, 
                    booked_from date,
                    booked_to date, 
                    room_num varchar(10), 
                    branch_id varchar(10), 
                    stat varchar(50), 
                    customer_id varchar(10),
                    foreign key(customer_id) references customer(customer_id),
                    foreign key(room_num,branch_id) references room(room_num,branch_id) );
create table accounts(log_date date,
                    branch_id varchar(10),
                    kitchen number(10,2), 
                    taxes number(10,2), 
                    bills number(10,2), 
                    other number(10,2),
                    wages number(10,2),
                    primary key(log_date,branch_id),
                    foreign key(branch_id) references branch(branch_id));
create table contain(bill_id varchar(10),
                    food_item varchar(100),
                    quantity number(10),
                    primary key(bill_id,food_item),
                    foreign key(bill_id) references invoice(bill_id),
                    foreign key(food_item) references food(item));
create table allotment(emp_id varchar(10), 
                    shift_id varchar(10), 
                    scheduled date,
                    primary key(emp_id,shift_id,scheduled),
                    foreign key(emp_id) references employee(emp_id) ,
                    foreign key(shift_id) references shift);
create table phone(customer_id varchar(10),
                    phone varchar(20),
                    foreign key(customer_id) references customer(customer_id));
create table users(emp_id varchar(10) primary key,
                    pin varchar(20),
                    clearance varchar(10),
                    foreign key(emp_id) references employee(emp_id));
create table messages(mess_id varchar(10) primary key,
                    sent_date date,
                    sender varchar(10),
                    mess varchar(500),
                    category varchar(20),
                    foreign key(sender) references employee(emp_id)
                    );
create table inbox(mess_id varchar(10),
                receipient varchar(10),
                disp varchar(1),
                primary key(mess_id,receipient),
                foreign key(mess_id) references messages(mess_id),
                foreign key(receipient) references employee(emp_id),
                check(disp in('y','n'))
                );