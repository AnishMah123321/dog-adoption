<?php

namespace App\Http\Controllers;


use App\UserPet;
use App\User;
use Illuminate\Http\Request;

class UserController extends Controller {

    function login(Request $request) {
        $email = $request->json()->get('email');
        $password = $request->json()->get('password');

        $userCount = User::where("email", $email)
            ->where("password", $password)
            ->count();

        if($userCount == 1) {
            return response()->json(["success" => true, "message" => "Valid Credentials"]);
        } else {
            return response()->json(["success" => false, "message" => "Invalid credentials."]);
        }
    }

    function register(Request $request) {
        $username = $request->json()->get('username');
        $password = $request->json()->get('password');
        $mobile = $request->json()->get('mobile');
        $email = $request->json()->get('email');
        $repassword = $request->json()->get('repassword');

        $user = new User();
        $user->username = $username;
        $user->password = $password;
        $user->mobile = $mobile;
        $user->email = $email;
        $user->repassword=$repassword;
        $user->save();

        return response()->json(["success" => true, "message" => "Registered Successfully"]);


    }
    public function logout(Request $request){
        $requestData=json_decode($request->getContent(),1);
        $em=$this->get('doctrine')->getEntityManager();
        if(isset($requestData['token'])){
            if($userToken=$em->getRepository('NavZUserBundle:UserToken')->findOneBy(array('token'=>$requestData['token']))){
                $em->remove($userToken);
                $em->flush($userToken);
                $response['status']='success';
                $response['msg']="Logged out successfully";
            }else{
                $response['status']='fail';
                $response['msg']="Mobile User is already logged out";
            }
        }else{
            $response['status']='fail';
            $response['msg']="Missing required parameter";
        }
        return new JsonResponse($response);
    }

}
