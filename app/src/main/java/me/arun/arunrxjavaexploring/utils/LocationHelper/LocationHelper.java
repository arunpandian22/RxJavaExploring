//package me.arun.arunrxjavaexploring.utils.LocationHelper;
//
//import android.Manifest;
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentSender;
//import android.location.Address;
//import android.location.Geocoder;
//import android.location.Location;
//import android.support.annotation.NonNull;
//import android.util.Log;
//import android.widget.Toast;
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GoogleApiAvailability;
//import com.google.android.gms.common.api.ApiException;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.common.api.ResolvableApiException;
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.location.LocationSettingsRequest;
//import com.google.android.gms.location.LocationSettingsResponse;
//import com.google.android.gms.location.LocationSettingsStatusCodes;
////import com.google.android.gms.tasks.OnCompleteListener;
////import com.google.android.gms.tasks.OnSuccessListener;
////import com.google.android.gms.tasks.Task;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//
//
///**
// * A Helper class created for the util functions related to the Location
// * Created by Jaison
// */
//
//public class LocationHelper implements PermissionUtils.OnPermissionRequestListener{
//
//    String TAG="LocationHelper";
//
//    private Context context;
//    private Activity current_activity;
//
//    private boolean isPermissionGranted;
//
//    private Location mLastLocation;
//
//    // Google client to interact with Google API
//
//    private GoogleApiClient mGoogleApiClient;
//
//    // list of permissions
//
//    private ArrayList<String> permissions=new ArrayList<>();
//    private PermissionUtils permissionUtils;
//
//    private final static int PLAY_SERVICES_REQUEST = 1000;
//    private final static int REQUEST_CHECK_SETTINGS = 2000;
//
//    FusedLocationProviderClient mFusedLocationClient;
//
//    public LocationHelper(Context context) {
//
//        this.context=context;
//        this.current_activity= (Activity) context;
//
//        permissionUtils=new PermissionUtils(context,this);
//
//        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
//        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
//
//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
//
//    }
//
//    /**
//     * Method to check the availability of location permissions
//     * */
//
//    public void checkpermission()
//    {
//        permissionUtils.checkPermission(permissions,"Need GPS permission for getting your location",1);
//    }
//
//    private boolean isPermissionGranted() {
//        return isPermissionGranted;
//    }
//
//    /**
//     * Method to verify google play services on the device
//     * */
//
//    public boolean checkPlayServices() {
//
//        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
//
//        int resultCode = googleApiAvailability.isGooglePlayServicesAvailable(context);
//
//        if (resultCode != ConnectionResult.SUCCESS) {
//            if (googleApiAvailability.isUserResolvableError(resultCode)) {
//                googleApiAvailability.getErrorDialog(current_activity,resultCode,
//                        PLAY_SERVICES_REQUEST).show();
//            } else {
//                showToast("This device is not supported.");
//            }
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * Method to display the location on UI
//     * */
//
//    public Location getLocation() {
//
//        Log.d(TAG, "getLocation: "+isPermissionGranted);
//        if (isPermissionGranted()) {
//
//            try
//            {
//                mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
//                    @Override
//                    public void onSuccess(Location location) {
//                        if (location != null) {
//                            // Logic to handle location object
//                            mLastLocation=location;
//                            Log.d(TAG, "onSuccess: ");
////                            ListadoSingleton.setmLastLocation(mLastLocation);
//                        }
//                    }
//                });
//
////                mLastLocation = LocationServices.FusedLocationApi
////                        .getLastLocation(mGoogleApiClient);
//
//                return mLastLocation;
//            }
//            catch (SecurityException e)
//            {
//                e.printStackTrace();
//
//            }
//
//        }
//
//        return null;
//
//    }
//
//    /**
//     * A method to get the address from the given lat and lang
//     *
//     * @param latitude  a param has the value of the latitude
//     * @param longitude a param has the value of the longitude
//     * @return it returns the address for the given latitude longitude
//     */
//    public Address getAddress(double latitude, double longitude) {
//        Geocoder geocoder;
//        List<Address> addresses;
//        geocoder = new Geocoder(context, Locale.getDefault());
//
//        try {
//            // Here 1 represent max location result to returned, by documents it recommended 1 to 5
//            addresses = geocoder.getFromLocation(latitude,longitude, 1);
//            return addresses.get(0);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//
//    }
//
//
//    /**
//     * Method used to build GoogleApiClient
//     */
//
//    public void buildGoogleApiClient() {
//        mGoogleApiClient = new GoogleApiClient.Builder(context)
//                .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) current_activity)
//                .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) current_activity)
//                .addApi(LocationServices.API).build();
//
//        mGoogleApiClient.connect();
//
//        LocationRequest mLocationRequest = new LocationRequest();
//        mLocationRequest.setInterval(10000);
//        mLocationRequest.setFastestInterval(5000);
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//
//        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
//                .addLocationRequest(mLocationRequest);
//
//        Task<LocationSettingsResponse> result =
//                LocationServices.getSettingsClient(context).checkLocationSettings(builder.build());
//
//        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
//            @Override
//            public void onComplete(Task<LocationSettingsResponse> task) {
//                try {
//                    LocationSettingsResponse response = task.getResult(ApiException.class);
//                    mLastLocation=getLocation();
//                } catch (ApiException exception) {
//                    switch (exception.getStatusCode()) {
//                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
//                            try {
//                                ResolvableApiException resolvable = (ResolvableApiException) exception;
//
//                                // Show the dialog by calling startResolutionForResult(),
//                                // and check the result in onActivityResult().
//                                resolvable.startResolutionForResult(current_activity, REQUEST_CHECK_SETTINGS);
//
//                            } catch (IntentSender.SendIntentException e) {
//                                // Ignore the error.
//                            }
//                            break;
//                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
//                            break;
//                    }
//                }
//            }
//        });
//    }
//
//    /**
//     * Method used to connect GoogleApiClient
//     */
//    public void connectApiClient()
//    {
//        mGoogleApiClient.connect();
//    }
//
//    /**
//     * Method used to get the GoogleApiClient
//     */
//    public GoogleApiClient getGoogleApiCLient()
//    {
//        return mGoogleApiClient;
//    }
//
//
//    /**
//     * Handles the permission results
//     */
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
//    {
//        permissionUtils.onRequestPermissionsResult(requestCode,permissions,grantResults);
//    }
//
//    /**
//     * Handles the activity results
//     */
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode) {
//            case REQUEST_CHECK_SETTINGS:
//                switch (resultCode) {
//                    case Activity.RESULT_OK:
//                        // All required changes were successfully made
//                        mLastLocation=getLocation();
//                        break;
//                    case Activity.RESULT_CANCELED:
//                        // The user was asked to change settings, but chose not to
//                        break;
//                    default:
//                        break;
//                }
//                break;
//        }
//    }
//
//
//    @Override
//    public void PermissionGranted(int request_code) {
//        Log.i("PERMISSION","GRANTED");
//        isPermissionGranted=true;
//    }
//
//    @Override
//    public void PartialPermissionGranted(int request_code, ArrayList<String> granted_permissions) {
//        Log.i("PERMISSION PARTIALLY","GRANTED");
//    }
//
//    @Override
//    public void PermissionDenied(int request_code) {
//        Log.i("PERMISSION","DENIED");
//    }
//
//    @Override
//    public void NeverAskAgain(int request_code) {
//        Log.i("PERMISSION","NEVER ASK AGAIN");
//    }
//
//
//    /**
//     * A method to show the Toast
//     * @param message a param has the value for the message which has to shown in toast
//     */
//    private void showToast(String message)
//    {
//        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
//    }
//
//
//}
