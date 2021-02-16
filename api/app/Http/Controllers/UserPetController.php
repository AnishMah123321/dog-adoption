<?php


namespace App\Http\Controllers;


use App\User;
use App\UserPet;
use Illuminate\Http\Request;

class UserPetController extends Controller
{
    function userPet(Request $request) {
        $name = $request->json()->get('name');
        $dog = $request->json()->get('dog');
        $breed = $request->json()->get('breed');
        $description =$request->json()->get('description');
        $number =$request->json()->get('number');
        $email = $request->json()->get('email');

        $userPet = new UserPet();
        $userPet->name = $name;
        $userPet->dog = $dog;
        $userPet->breed = $breed;
        $userPet->description = $description;
        $userPet->number=$number;
        $userPet->email =$email;
        $userPet->save();
        return response()->json(["success" => true, "message" => "Listed Successfully"]);

    }

    function all() {
        return UserPet::all();
    }
}
