create table Todo{
    id serial primary key,
    title varchar(20) not null,
    description varchar(255),
    isCompleted boolean default false,
    created_at timestamp default current_date,
    updated_at timestamp default current_date
};