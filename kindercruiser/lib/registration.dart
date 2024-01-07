// registration.dart
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

import 'package:flutter/material.dart';

class RegistrationForm extends StatefulWidget {
  @override
  _RegistrationFormState createState() => _RegistrationFormState();
}

class _RegistrationFormState extends State<RegistrationForm> {
  final TextEditingController _usernameController = TextEditingController();
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('kN0wTeCh Tamil'),
      ),
      body: SingleChildScrollView(
        child: Center(
          child: Padding(
            padding: const EdgeInsets.all(16.0),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                CircleAvatar(
                  radius: 50.0, // Adjust the radius as needed
                  backgroundColor: Colors.black,
                  child: ClipOval(
                    child: Image.asset(
                      'assets/logo.png',
                      height: 120.0, // Adjust the height as needed
                      width: 120.0, // Adjust the width as needed
                      fit: BoxFit.cover,
                    ),
                  ),
                ),
                SizedBox(height: 20.0),
                TextField(
                  controller: _usernameController,
                  decoration: InputDecoration(
                    labelText: 'Username',
                    border: OutlineInputBorder(
                      borderSide: BorderSide(color: Colors.black),
                      borderRadius: BorderRadius.circular(10.0),
                    ),
                  ),
                ),
                SizedBox(height: 10.0),
                TextField(
                  controller: _emailController,
                  decoration: InputDecoration(
                    labelText: 'Email',
                    border: OutlineInputBorder(
                      borderSide: BorderSide(color: Colors.black),
                      borderRadius: BorderRadius.circular(10.0),
                    ),
                  ),
                ),
                SizedBox(height: 10.0),
                TextField(
                  controller: _passwordController,
                  decoration: InputDecoration(
                    labelText: 'Password',
                    border: OutlineInputBorder(
                      borderSide: BorderSide(color: Colors.black),
                      borderRadius: BorderRadius.circular(10.0),
                    ),
                  ),
                  obscureText: true,
                ),
                SizedBox(height: 20.0),
                ElevatedButton(
                  onPressed: _registerUser,
                  style: ElevatedButton.styleFrom(
                    primary: Colors.black,
                    onPrimary: Color(0xFFCDBD5B),
                  ),
                  child: Text('Signup'),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  // Widget _buildTextField(TextEditingController controller, String labelText, {bool isPassword = false}) {
  //   return Container(
  //     child: TextField(
  //       controller: controller,
  //       decoration: InputDecoration(
  //         labelText: labelText,
  //         border: OutlineInputBorder(
  //           borderRadius: BorderRadius.circular(15.0), // Adjust the border radius
  //         ),
  //       ),
  //       obscureText: isPassword,
  //     ),
  //   );
  // }

  void _registerUser() async {
    String username = _usernameController.text;
    String email = _emailController.text;
    String password = _passwordController.text;

    final String apiUrl = 'http://192.168.199.174:8090/api/v1/user/registerUser';

    try{
      final response = await http.post(
        Uri.parse(apiUrl),
        headers: {'Content-Type': 'application/json'},
        body: jsonEncode({'username': username, 'email': email, 'password': password}),
      );

      if (response.statusCode == 201) {
        // Navigator.push(
        //   context,
        //   MaterialPageRoute(builder: (context) => Dashboard()), // Replace Dashboard() with your actual Dashboard screen
        // );
        print('User registered successfully');
      } else if (response.statusCode == 400) {
        // Handle validation errors
        Map<String, dynamic> errorBody = jsonDecode(response.body);
        String errorMessage = errorBody['message']; // Adjust based on your Spring Boot response structure

        // Display error message to the user
        _showErrorDialog(errorMessage);
      } else {
        // Registration failed for other reasons, handle accordingly
        print('Failed to register user. Status code: ${response.statusCode}');
      }

    }catch(e){
      // Handle network or other errors
      print('Error during registration: $e');
    }

    // Add your registration logic, such as sending data to a server or validating input
    // For simplicity, we print the values here
    print('Username: $username, Email: $email, Password: $password');
  }
  void _showErrorDialog(String errorMessage) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text('Error'),
          content: Text(errorMessage),
          actions: <Widget>[
            TextButton(
              onPressed: () {
                Navigator.of(context).pop();
              },
              child: Text('OK'),
            ),
          ],
        );
      },
    );
  }

}
