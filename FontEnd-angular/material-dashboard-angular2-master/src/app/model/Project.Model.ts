import { Task } from "./Task.Model";
import { User } from "./User.Model";

export class Project{
    id!:String;
    name!:string;
    description:String;
    users:Array<User>;
    task:Array<Task>
    
}