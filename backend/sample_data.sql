insert into branch values('NY101','Baker','New York',null,1234,'active');
insert into employee values('2001','Albert','Einstein','M','tunneling predictor',current_date,null,1000000,'234','NY101');
insert into customer values('1000','abc','xyz','abc@gmail.com','567','789','M');
insert into food values('Chicken Steak',350,0,'y');
insert into food values('Dragon Chicken',350,0,'y');
insert into food values('Drums of Heaven',350,0,'y');
insert into food values('Tripple Schezuan',350,0,'y');
insert into food values('Fried Rice',350,0,'y');
insert into room values('NY101','305','3','vacant','y',5000);
insert into room values('NY101','306','3','vacant','y',5000);
insert into room values('NY101','307','3','vacant','y',5000);
insert into room values('NY101','308','3','vacant','y',5000);
insert into room values('NY101','309','3','vacant','y',5000);
insert into invoice values('1000',current_date,1000,null,'1000','NY101');
insert into invoice values('1001',current_date,1500,null,'1000','NY101');
insert into invoice values('1002',current_date,1200,null,'1000','NY101');
insert into invoice values('1003',to_date('25/4/2020','DD-MM-YYYY'),1200,null,'1000','NY101');
insert into invoice values('1004',to_date('25/4/2020','DD-MM-YYYY'),1500,null,'1000','NY101');
insert into accounts values(current_date,'NY101',200,100,1000,500,null);
insert into accounts values(to_date('25/4/2020','DD-MM-YYYY'),'NY101',200,100,1000,500,null);
insert into stay values('1000','1000',to_date('20/4/2020','DD-MM-YYYY'),to_date('25/4/2020','DD-MM-YYYY'),'305','NY101',500,100);
insert into stay values('1001','1000',to_date('14/4/2020','DD-MM-YYYY'),to_date('20/4/2020','DD-MM-YYYY'),'306','NY101',1000,100);