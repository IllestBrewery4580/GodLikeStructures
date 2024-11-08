//Step one Read Config
import groovy.json.JsonSlurper
import groovy.json.JsonOutput

def slurper = new JsonSlurper()
def result = slurper.parseText('''
{
    "Domain": "NewcastleSensor", 
    "TemplateName": "SensorTheme",  
    "TemplateDescription": "Used to create Themes for sensor collection in Newcastle",  
    "DataMeshURL": "https://newcastle.urbanobservatory.ac.uk/api/v1.1/themes/json/",  
    "Splitter": [
        {
            "SplitterPath": "",  
            "TopicArea": [
                {
                    "DataMeshName": ".Themes[*].Name", 
                    "DataMeshTopic": "SensorTheme", 
                    "ABAC": [
                        {
                            "apiVersion": "abac.authorization.polyphaze.v1beta1", 
                            "kind": "Policy", 
                            "spec": [
                                {
                                    "user": "kubelet", 
                                    "namespace": "*", 
                                    "resource": "events"
                                }
                            ]
                        }, 
                        {
                            "apiVersion": "abac.authorization.polyphaze.v1beta1", 
                            "kind": "Policy", 
                            "spec": [
                                {
                                    "user": "bob", 
                                    "namespace": "projectCaribou", 
                                    "resource": "pods", 
                                    "readonly": true
                                }
                            ]
                        }
                    ], 
                    "Relationship": []
                }
            ]
        }
    ],  
    "Stream": []
}
''')

// Retrieve and interpolate DataMeshNamePath
def strDataMeshNamePath = result.Splitter[0].TopicArea[0].DataMeshName
println "DataMeshName Path: $strDataMeshNamePath"

// Create the JOLT spec with the interpolated DataMeshNamePath
def strJoltOutput = '''{
    "operation": "shift", 
    "spec": {
        "Themes": {
            "*": {
                "$strDataMeshNamePath": "DataSet.Features[].Name"
            }
        }
    }
}'''
println "JOLT Spec with Interpolated DataMeshNamePath: $strJoltOutput"

// Simulate transformation function
def joltTransform(inputJson, joltSpec) {
    // Simulated transformation output
    def simulatedOutput = [DataSet:[Features: [Name: ["Theme1", "Theme2"]]]]
    return simulatedOutput
}

// Sample input
def validInput = '''
{
    "Themes": [
        {"Name": "Theme1"},
        {"Name": "Theme2"}
    ]
}
'''

// Perform the transformation
def transformedOutput = joltTransform(validInput, strJoltOutput)

// Function to format and print the output
def formatOutput(output) {
    println "Formatted Transformation Output:"
    println JsonOutput.prettyPrint(JsonOutput.toJson(output))
}

// Call the formatOutput function if the transformation is successful
if (transformedOutput) {
    formatOutput(transformedOutput)
//Step one Read Config
import groovy.json.JsonSlurper
import groovy.json.JsonOutput

def slurper = new JsonSlurper()
def result = slurper.parseText('''
{
    "Domain": "NewcastleSensor", 
    "TemplateName": "SensorTheme",  
    "TemplateDescription": "Used to create Themes for sensor collection in Newcastle",  
    "DataMeshURL": "https://newcastle.urbanobservatory.ac.uk/api/v1.1/themes/json/",  
    "Splitter": [
        {
            "SplitterPath": "",  
            "TopicArea": [
                {
                    "DataMeshName": ".Themes[*].Name", 
                    "DataMeshTopic": "SensorTheme", 
                    "ABAC": [
                        {
                            "apiVersion": "abac.authorization.polyphaze.v1beta1", 
                            "kind": "Policy", 
                            "spec": [
                                {
                                    "user": "kubelet", 
                                    "namespace": "*", 
                                    "resource": "events"
                                }
                            ]
                        }, 
                        {
                            "apiVersion": "abac.authorization.polyphaze.v1beta1", 
                            "kind": "Policy", 
                            "spec": [
                                {
                                    "user": "bob", 
                                    "namespace": "projectCaribou", 
                                    "resource": "pods", 
                                    "readonly": true
                                }
                            ]
                        }
                    ], 
                    "Relationship": []
                }
            ]
        }
    ],  
    "Stream": []
}
''')

// Retrieve and interpolate DataMeshNamePath
def strDataMeshNamePath = result.Splitter[0].TopicArea[0].DataMeshName
println "DataMeshName Path: $strDataMeshNamePath"

// Create the JOLT spec with the interpolated DataMeshNamePath
def strJoltOutput = '''{
    "operation": "shift", 
    "spec": {
        "Themes": {
            "*": {
                "$strDataMeshNamePath": "DataSet.Features[].Name"
            }
        }
    }
}'''
println "JOLT Spec with Interpolated DataMeshNamePath: $strJoltOutput"

// Simulate transformation function
def joltTransform(inputJson, joltSpec) {
    // Simulated transformation output
    def simulatedOutput = [DataSet:[Features: [Name: ["Theme1", "Theme2"]]]]
    return simulatedOutput
}

// Sample input
def validInput = '''
{
    "Themes": [
        {"Name": "Theme1"},
        {"Name": "Theme2"}
    ]
}
'''

// Perform the transformation
def transformedOutput = joltTransform(validInput, strJoltOutput)

// Function to format and print the output
def formatOutput(output) {
    println "Formatted Transformation Output:"
    println JsonOutput.prettyPrint(JsonOutput.toJson(output))
}

// Call the formatOutput function if the transformation is successful
if (transformedOutput) {
    formatOutput(transformedOutput)
}
